package com.maxmind.minfraud;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static com.maxmind.minfraud.request.RequestTestHelper.fullTransaction;
import static com.maxmind.minfraud.request.RequestTestHelper.fullTransactionEmailMd5;
import static com.maxmind.minfraud.request.RequestTestHelper.fullTransactionReport;
import static com.maxmind.minfraud.request.RequestTestHelper.readJsonFile;
import static com.maxmind.minfraud.request.RequestTestHelper.verifyRequestFor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.maxmind.minfraud.exception.AuthenticationException;
import com.maxmind.minfraud.exception.HttpException;
import com.maxmind.minfraud.exception.InsufficientFundsException;
import com.maxmind.minfraud.exception.InvalidRequestException;
import com.maxmind.minfraud.exception.MinFraudException;
import com.maxmind.minfraud.response.EmailDomain;
import com.maxmind.minfraud.response.EmailDomainVisit;
import java.time.LocalDate;
import com.maxmind.minfraud.exception.PermissionRequiredException;
import com.maxmind.minfraud.request.Device;
import com.maxmind.minfraud.request.Shipping;
import com.maxmind.minfraud.request.Transaction;
import com.maxmind.minfraud.request.TransactionReport;
import com.maxmind.minfraud.response.FactorsResponse;
import com.maxmind.minfraud.response.InsightsResponse;
import com.maxmind.minfraud.response.IpRiskReason;
import com.maxmind.minfraud.response.ScoreResponse;
import java.net.InetAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;

@WireMockTest
public class WebServiceClientTest {
    @RegisterExtension
    static WireMockExtension wireMock = WireMockExtension.newInstance()
        .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
        .build();

    @Test
    public void testReportTransaction() throws Exception {
        var responseContent = "";
        var client = createSuccessClient("transactions/report", 204,
            responseContent);
        var request = fullTransactionReport();
        client.reportTransaction(request);
    }

    @Test
    public void testFullScoreTransaction() throws Exception {
        var responseContent = readJsonFile("score-response");
        var client = createSuccessClient("score", 200, responseContent);
        var request = fullTransaction();
        var response = client.score(request);

        JSONAssert.assertEquals(responseContent, response.toJson(), true);
        verifyRequestFor(wireMock, "score", "full-request");
    }

    @Test
    public void testFullScoreTransactionWithEmailMd5() throws Exception {
        var responseContent = readJsonFile("score-response");
        var client = createSuccessClient("score", 200, responseContent);
        var request = fullTransactionEmailMd5();
        var response = client.score(request);

        JSONAssert.assertEquals(responseContent, response.toJson(), true);
        verifyRequestFor(wireMock, "score", "full-request-email-md5");
    }

    @Test
    public void testFullInsightsTransaction() throws Exception {
        var responseContent = readJsonFile("insights-response");
        var client = createSuccessClient("insights", 200, responseContent);
        var request = fullTransaction();
        var response = client.insights(request);

        // We use non-strict checking as there is some extra stuff in the serialized
        // object, most notably the "name" field in the GeoIP2 InsightsResponse subobjects.
        // We cannot change this as it would be a breaking change to the GeoIP2 API.
        JSONAssert.assertEquals(responseContent, response.toJson(), false);
        verifyRequestFor(wireMock, "insights", "full-request");
        assertTrue(
            response.ipAddress().country().isInEuropeanUnion(),
            "response.ipAddress().country().isInEuropeanUnion() does not return true"
        );
        assertFalse(
            response.ipAddress().registeredCountry().isInEuropeanUnion(),
            "response.ipAddress().registeredCountry().isInEuropeanUnion() does not return false"
        );
        assertTrue(
            response.ipAddress().representedCountry().isInEuropeanUnion(),
            "response.ipAddress().representedCountry().isInEuropeanUnion() does not return true"
        );
        assertEquals("2018-04-05T15:34:40-07:00", response.device().localTime());

        assertEquals("152.216.7.110", response.ipAddress().traits().ipAddress().getHostAddress());
        assertEquals("81.2.69.0/24",
            response.ipAddress().traits().network().toString());

        assertTrue(response.creditCard().isVirtual());

        // Test email domain fields
        assertEquals(EmailDomain.Classification.EDUCATION, response.email().domain().classification());
        assertEquals(15.5, response.email().domain().risk());
        assertEquals(630000.0, response.email().domain().volume());
        assertNotNull(response.email().domain().visit());
        assertEquals(EmailDomainVisit.Status.LIVE, response.email().domain().visit().status());
        assertTrue(response.email().domain().visit().hasRedirect());
        assertEquals(LocalDate.parse("2024-11-15"), response.email().domain().visit().lastVisitedOn());

        var reasons = response.ipAddress().riskReasons();

        assertEquals(2, reasons.size(), "two IP risk reasons");
        assertEquals(
            "MINFRAUD_NETWORK_ACTIVITY",
            reasons.get(1).code(),
            "second IP risk reason code"
        );
    }

    @Test
    public void testFullFactorsTransaction() throws Exception {
        var responseContent = readJsonFile("factors-response");
        var client = createSuccessClient("factors", 200, responseContent);
        var request = fullTransaction();
        var response = client.factors(request);

        // We use non-strict checking as there is some extra stuff in the serialized
        // object, most notably the "name" field in the GeoIP2 InsightsResponse subobjects.
        // We cannot change this as it would be a breaking change to the GeoIP2 API.
        JSONAssert.assertEquals(responseContent, response.toJson(), false);
        verifyRequestFor(wireMock, "factors", "full-request");
        assertTrue(
            response.ipAddress().country().isInEuropeanUnion(),
            "response.ipAddress().country().isInEuropeanUnion() does not return true"
        );
        assertTrue(
            response.ipAddress().registeredCountry().isInEuropeanUnion(),
            "response.ipAddress().registeredCountry().isInEuropeanUnion() does not return true"
        );
        assertFalse(
            response.ipAddress().representedCountry().isInEuropeanUnion(),
            "response.ipAddress().representedCountry().isInEuropeanUnion() does not return false"
        );

        // Test email domain fields
        assertEquals(EmailDomain.Classification.ISP_EMAIL, response.email().domain().classification());
        assertEquals(25.0, response.email().domain().risk());
        assertEquals(500000.5, response.email().domain().volume());
        assertNotNull(response.email().domain().visit());
        assertEquals(EmailDomainVisit.Status.PARKED, response.email().domain().visit().status());
        assertFalse(response.email().domain().visit().hasRedirect());
        assertEquals(LocalDate.parse("2024-10-20"), response.email().domain().visit().lastVisitedOn());

        assertEquals("152.216.7.110", response.ipAddress().traits().ipAddress().getHostAddress());
        assertEquals("81.2.69.0/24",
            response.ipAddress().traits().network().toString());
    }

    @Test
    public void testRequestEncoding() throws Exception {
        var client = createSuccessClient("insights", 200, "{}");
        var request = new Transaction.Builder(
            new Device.Builder(InetAddress.getByName("1.1.1.1")).build()
        ).shipping(
            new Shipping.Builder()
                .firstName("Allan dias á s maia")
                .build()
        ).build();
        client.insights(request);

        wireMock.verify(postRequestedFor(urlMatching("/minfraud/v2.0/insights"))
            .withRequestBody(equalToJson(
                "{\"device\":{\"ip_address\":\"1.1.1.1\"},\"shipping\":{\"first_name\":\"Allan dias á s maia\"}}")));
    }

    @Test
    public void test200WithNoBody() throws Exception {
        var client = createSuccessClient("insights", 200, "");
        var request = fullTransaction();
        var ex = assertThrows(MinFraudException.class, () -> client.insights(request));

        assertThat(ex.getMessage(),
            matchesPattern("Received a 200 response but could not decode it as JSON"));
    }

    @Test
    public void test200WithInvalidJson() throws Exception {
        var client = createSuccessClient("insights", 200, "{");
        var request = fullTransaction();

        var ex = assertThrows(MinFraudException.class, () -> client.insights(request));

        assertEquals("Received a 200 response but could not decode it as JSON",
            ex.getMessage());
    }

    @Test
    public void testInsufficientCredit() {
        var ex = assertThrows(InsufficientFundsException.class, () -> createInsightsError(
            402,
            "application/json",
            "{\"code\":\"INSUFFICIENT_FUNDS\",\"error\":\"out of credit\"}"
        ));
        assertEquals("out of credit", ex.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"ACCOUNT_ID_REQUIRED",
        "AUTHORIZATION_INVALID",
        "LICENSE_KEY_REQUIRED",
        "USER_ID_REQUIRED"})
    public void testInvalidAuth(String code) {
        var ex = assertThrows(AuthenticationException.class, () ->
            createInsightsError(
                401,
                "application/json",
                "{\"code\":\"" + code + "\",\"error\":\"Invalid auth\"}"
            )
        );
        assertEquals("Invalid auth", ex.getMessage());
    }

    @Test
    public void testPermissionRequired() {
        var ex = assertThrows(PermissionRequiredException.class, () -> createInsightsError(
            403,
            "application/json",
            "{\"code\":\"PERMISSION_REQUIRED\",\"error\":\"Permission required\"}"
        ));
        assertEquals("Permission required", ex.getMessage());
    }

    @Test
    public void testInvalidRequest() {
        var ex = assertThrows(InvalidRequestException.class, () ->
            createInsightsError(
                400,
                "application/json",
                "{\"code\":\"IP_ADDRESS_INVALID\",\"error\":\"IP invalid\"}"
            )
        );
        assertEquals("IP invalid", ex.getMessage());
    }

    @Test
    public void test400WithInvalidJson() {
        var ex = assertThrows(HttpException.class, () ->
            createInsightsError(
                400,
                "application/json",
                "{blah}"
            )
        );
        assertThat(ex.getMessage(), matchesPattern(
            "Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body: \\{blah\\}"));
    }

    @Test
    public void test400WithNoBody() {
        var ex = assertThrows(HttpException.class, () ->
            createInsightsError(
                400,
                "application/json",
                ""
            )
        );
        assertThat(ex.getMessage(), matchesPattern(
            "Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body:.*"));
    }

    @Test
    public void test400WithUnexpectedContentType() {
        var ex = assertThrows(HttpException.class, () ->
            createInsightsError(
                400,
                "text/plain",
                "text"
            )
        );
        assertThat(ex.getMessage(), matchesPattern(
            "Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body: text"));

    }

    @Test
    public void test400WithUnexpectedJson() {
        var ex = assertThrows(HttpException.class, () ->
            createInsightsError(
                400,
                "application/json",
                "{\"not\":\"expected\"}"
            )
        );
        assertEquals(
            "Error response contains JSON but it does not specify code or error keys: {\"not\":\"expected\"}",
            ex.getMessage());
    }

    @Test
    public void test300() {
        var ex = assertThrows(HttpException.class, () ->
            createInsightsError(
                300,
                "application/json",
                ""
            )
        );
        assertThat(ex.getMessage(), startsWith("Received an unexpected HTTP status (300)"));

    }

    @Test
    public void test500() {
        var ex = assertThrows(HttpException.class, () ->
            createInsightsError(
                500,
                "application/json",
                ""
            )
        );
        assertThat(ex.getMessage(), startsWith("Received a server error (500)"));
    }

    private WebServiceClient createSuccessClient(String service, int code, String responseContent) {
        return createClient(
            service,
            code,
            "application/vnd.maxmind.com-minfraud-" + service
                + "+json; charset=UTF-8; version=2.0\n",
            responseContent
        );
    }

    private void createInsightsError(int status, String contentType, String responseContent)
        throws Exception {
        createClient(
            "insights",
            status,
            contentType,
            responseContent
        ).insights(fullTransaction());
    }

    private WebServiceClient createClient(String service, int status, String contentType,
                                          String responseContent) {
        wireMock.stubFor(post(urlEqualTo("/minfraud/v2.0/" + service))
            .withHeader("Accept", equalTo("application/json"))
            .willReturn(aResponse()
                .withStatus(status)
                .withHeader("Content-Type", contentType)
                .withBody(responseContent)));

        return new WebServiceClient.Builder(6, "0123456789")
            .host("localhost")
            .port(wireMock.getPort())
            .disableHttps()
            .build();
    }

    @Test
    public void testHttpClientWorks() {
        var customClient = HttpClient.newBuilder().build();

        var client = new WebServiceClient.Builder(6, "0123456789")
            .httpClient(customClient)
            .build();

        // Verify the client was created successfully
        assertEquals("WebServiceClient{host='minfraud.maxmind.com', port=443, useHttps=true, locales=[en], httpClient=" + customClient + "}", client.toString());
    }

    @Test
    public void testHttpClientWithConnectTimeoutThrowsException() {
        var customClient = HttpClient.newBuilder().build();

        var ex = assertThrows(IllegalArgumentException.class, () ->
            new WebServiceClient.Builder(6, "0123456789")
                .httpClient(customClient)
                .connectTimeout(Duration.ofSeconds(5))
                .build()
        );

        assertEquals("Cannot set both httpClient and connectTimeout. " +
            "Configure timeouts on the provided HttpClient instead.", ex.getMessage());
    }

    @Test
    public void testHttpClientWithProxyThrowsException() {
        var customClient = HttpClient.newBuilder().build();

        var ex = assertThrows(IllegalArgumentException.class, () ->
            new WebServiceClient.Builder(6, "0123456789")
                .httpClient(customClient)
                .proxy(java.net.ProxySelector.of(null))
                .build()
        );

        assertEquals("Cannot set both httpClient and proxy. " +
            "Configure proxy on the provided HttpClient instead.", ex.getMessage());
    }
}
