package com.maxmind.minfraud;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.maxmind.minfraud.exception.*;
import com.maxmind.minfraud.request.Device;
import com.maxmind.minfraud.request.Shipping;
import com.maxmind.minfraud.request.Transaction;
import com.maxmind.minfraud.request.TransactionReport;
import com.maxmind.minfraud.response.FactorsResponse;
import com.maxmind.minfraud.response.InsightsResponse;
import com.maxmind.minfraud.response.IpRiskReason;
import com.maxmind.minfraud.response.ScoreResponse;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

import java.net.InetAddress;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static com.maxmind.minfraud.request.RequestTestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class WebServiceClientTest {
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(0); // 0 picks random port

    @Test
    public void testReportTransaction() throws Exception {
        String responseContent = "";
        try (WebServiceClient client = createSuccessClient("transactions/report", 204, responseContent)) {
            TransactionReport request = fullTransactionReport();
            client.reportTransaction(request);
        }
    }

    @Test
    public void testFullScoreTransaction() throws Exception {
        String responseContent = readJsonFile("score-response");
        try (WebServiceClient client = createSuccessClient("score", 200, responseContent)) {
            Transaction request = fullTransaction();
            ScoreResponse response = client.score(request);

            JSONAssert.assertEquals(responseContent, response.toJson(), true);
            verifyRequestFor("score", "full-request");
        }
    }

    @Test
    public void testFullScoreTransactionWithEmailMd5() throws Exception {
        String responseContent = readJsonFile("score-response");
        try (WebServiceClient client = createSuccessClient("score", 200, responseContent)) {
            Transaction request = fullTransactionEmailMd5();
            ScoreResponse response = client.score(request);

            JSONAssert.assertEquals(responseContent, response.toJson(), true);
            verifyRequestFor("score", "full-request-email-md5");
        }
    }

    @Test
    public void testFullInsightsTransaction() throws Exception {
        String responseContent = readJsonFile("insights-response");
        try (WebServiceClient client = createSuccessClient("insights", 200, responseContent)) {
            Transaction request = fullTransaction();
            InsightsResponse response = client.insights(request);

            // We use non-strict checking as there is some extra stuff in the serialized
            // object, most notably the "name" field in the GeoIP2 InsightsResponse subobjects.
            // We cannot change this as it would be a breaking change to the GeoIP2 API.
            JSONAssert.assertEquals(responseContent, response.toJson(), false);
            verifyRequestFor("insights", "full-request");
            assertTrue(
                    "response.getIpAddress().getCountry().isInEuropeanUnion() does not return true",
                    response.getIpAddress().getCountry().isInEuropeanUnion());
            assertFalse(
                    "response.getIpAddress().getRegisteredCountry().isInEuropeanUnion() does not return false",
                    response.getIpAddress().getRegisteredCountry().isInEuropeanUnion());
            assertTrue(
                    "response.getIpAddress().getRepresentedCountry().isInEuropeanUnion() does not return true",
                    response.getIpAddress().getRepresentedCountry().isInEuropeanUnion());
            assertEquals("2018-04-05T15:34:40-07:00", response.getDevice().getLocalTime());

            assertEquals("152.216.7.110", response.getIpAddress().getTraits().getIpAddress());
            assertEquals("81.2.69.0/24", response.getIpAddress().getTraits().getNetwork().toString());

            assertTrue(response.getCreditCard().isVirtual());

            List<IpRiskReason> reasons = response.getIpAddress().getRiskReasons();

            assertEquals("two IP risk reasons", 2, reasons.size());
            assertEquals("second IP risk reason code", "MINFRAUD_NETWORK_ACTIVITY",
                    reasons.get(1).getCode());
        }
    }

    @Test
    public void testFullFactorsTransaction() throws Exception {
        String responseContent = readJsonFile("factors-response");
        try (WebServiceClient client = createSuccessClient("factors", 200, responseContent)) {
            Transaction request = fullTransaction();
            FactorsResponse response = client.factors(request);

            // We use non-strict checking as there is some extra stuff in the serialized
            // object, most notably the "name" field in the GeoIP2 InsightsResponse subobjects.
            // We cannot change this as it would be a breaking change to the GeoIP2 API.
            JSONAssert.assertEquals(responseContent, response.toJson(), false);
            verifyRequestFor("factors", "full-request");
            assertTrue(
                    "response.getIpAddress().getCountry().isInEuropeanUnion() does not return true",
                    response.getIpAddress().getCountry().isInEuropeanUnion());
            assertTrue(
                    "response.getIpAddress().getRegisteredCountry().isInEuropeanUnion() does not return true",
                    response.getIpAddress().getRegisteredCountry().isInEuropeanUnion());
            assertFalse(
                    "response.getIpAddress().getRepresentedCountry().isInEuropeanUnion() does not return false",
                    response.getIpAddress().getRepresentedCountry().isInEuropeanUnion());


            assertEquals("152.216.7.110", response.getIpAddress().getTraits().getIpAddress());
            assertEquals("81.2.69.0/24", response.getIpAddress().getTraits().getNetwork().toString());
        }
    }

    @Test
    public void testRequestEncoding() throws Exception {
        try (WebServiceClient client = createSuccessClient("insights", 200, "{}")) {
            Transaction request = new Transaction.Builder(
                    new Device.Builder(InetAddress.getByName("1.1.1.1")).build()
            ).shipping(
                    new Shipping.Builder()
                            .firstName("Allan dias á s maia")
                            .build()
            ).build();
            client.insights(request);

            verify(postRequestedFor(urlMatching("/minfraud/v2.0/insights"))
                    .withRequestBody(equalToJson("{\"device\":{\"ip_address\":\"1.1.1.1\"},\"shipping\":{\"first_name\":\"Allan dias á s maia\"}}")));
        }
    }

    @Test
    public void test200WithNoBody() throws Exception {
        try (WebServiceClient client = createSuccessClient("insights", 200, "")) {
            Transaction request = fullTransaction();
            Exception ex = assertThrows(MinFraudException.class, () -> client.insights(request));

            assertThat(ex.getMessage(), matchesPattern("Received a 200 response but could not decode it as JSON"));
        }
    }

    @Test
    public void test200WithInvalidJson() throws Exception {
        try (WebServiceClient client = createSuccessClient("insights", 200, "{")) {
            Transaction request = fullTransaction();

            Exception ex = assertThrows(MinFraudException.class, () -> client.insights(request));

            assertEquals("Received a 200 response but could not decode it as JSON", ex.getMessage());
        }
    }

    @Test
    public void testInsufficientCredit() {
        Exception ex = assertThrows(InsufficientFundsException.class, () -> createInsightsError(
                402,
                "application/json",
                "{\"code\":\"INSUFFICIENT_FUNDS\",\"error\":\"out of credit\"}"
        ));
        assertEquals("out of credit", ex.getMessage());

    }

    @Test
    @Parameters({"ACCOUNT_ID_REQUIRED",
            "AUTHORIZATION_INVALID",
            "LICENSE_KEY_REQUIRED",
            "USER_ID_REQUIRED"})
    public void testInvalidAuth(String code) {
        Exception ex = assertThrows(AuthenticationException.class, () ->
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
        Exception ex = assertThrows(PermissionRequiredException.class, () -> createInsightsError(
                403,
                "application/json",
                "{\"code\":\"PERMISSION_REQUIRED\",\"error\":\"Permission required\"}"
        ));
        assertEquals("Permission required", ex.getMessage());
    }

    @Test
    public void testInvalidRequest() {
        Exception ex = assertThrows(InvalidRequestException.class, () ->
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
        Exception ex = assertThrows(HttpException.class, () ->
                createInsightsError(
                        400,
                        "application/json",
                        "{blah}"
                )
        );
        assertThat(ex.getMessage(), matchesPattern("Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body: \\{blah\\}"));
    }

    @Test
    public void test400WithNoBody() {
        Exception ex = assertThrows(HttpException.class, () ->
                createInsightsError(
                        400,
                        "application/json",
                        ""
                )
        );
        assertThat(ex.getMessage(), matchesPattern("Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body:.*"));
    }

    @Test
    public void test400WithUnexpectedContentType() {
        Exception ex = assertThrows(HttpException.class, () ->
                createInsightsError(
                        400,
                        "text/plain",
                        "text"
                )
        );
        assertThat(ex.getMessage(), matchesPattern("Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body: text"));

    }

    @Test
    public void test400WithUnexpectedJson() {
        Exception ex = assertThrows(HttpException.class, () ->
                createInsightsError(
                        400,
                        "application/json",
                        "{\"not\":\"expected\"}"
                )
        );
        assertEquals("Error response contains JSON but it does not specify code or error keys: {\"not\":\"expected\"}", ex.getMessage());
    }

    @Test
    public void test300() {
        Exception ex = assertThrows(HttpException.class, () ->
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
        Exception ex = assertThrows(HttpException.class, () ->
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
                "application/vnd.maxmind.com-minfraud-" + service + "+json; charset=UTF-8; version=2.0\n",
                responseContent
        );
    }

    private void createInsightsError(int status, String contentType, String responseContent) throws Exception {
        try (WebServiceClient client = createClient(
                "insights",
                status,
                contentType,
                responseContent
        )) {
            client.insights(fullTransaction());
        }
    }

    private WebServiceClient createClient(String service, int status, String contentType, String responseContent) {
        stubFor(post(urlEqualTo("/minfraud/v2.0/" + service))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", contentType)
                        .withBody(responseContent)));

        return new WebServiceClient.Builder(6, "0123456789")
                .host("localhost")
                .port(this.wireMockRule.port())
                .disableHttps()
                .build();
    }
}
