package com.maxmind.minfraud;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.maxmind.minfraud.exception.*;
import com.maxmind.minfraud.request.InsightsRequest;
import com.maxmind.minfraud.request.ScoreRequest;
import com.maxmind.minfraud.response.InsightsResponse;
import com.maxmind.minfraud.response.ScoreResponse;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static com.maxmind.minfraud.request.RequestTestHelper.*;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(JUnitParamsRunner.class)
public class WebServiceClientTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(0); // 0 picks random port

    @Test
    public void testFullScoreRequest() throws Exception {
        String responseContent = readJsonFile("score-response");
        WebServiceClient client = this.createSuccessClient("score", responseContent);
        ScoreRequest request = fullScoreRequest();
        ScoreResponse response = client.score(request);

        JSONAssert.assertEquals(responseContent, response.toJson(), true);
        verifyRequestFor("score");
    }

    @Test
    public void testFullInsightsRequest() throws Exception {
        String responseContent = readJsonFile("insights-response");
        WebServiceClient client = this.createSuccessClient("insights", responseContent);
        InsightsRequest request = fullInsightsRequest();
        InsightsResponse response = client.insights(request);

        // We use non-strict checking as there is some extra stuff in the serialized
        // object, most notably the "name" field in the GeoIP2 InsightsResponse subobjects.
        // We cannot change this as it would be a breaking change to the GeoIP2 API.
        JSONAssert.assertEquals(responseContent, response.toJson(), false);
        verifyRequestFor("insights");
    }

    @Test
    public void test200WithNoBody() throws Exception {
        WebServiceClient client = createSuccessClient("insights", "");
        InsightsRequest request = fullInsightsRequest();

        thrown.expect(HttpException.class);
        thrown.expectMessage(matchesPattern("Received a 200 response for .*/minfraud/v2.0/insights but there was no message body\\."));
        client.insights(request);
    }

    @Test
    public void test200WithInvalidJson() throws Exception {
        WebServiceClient client = createSuccessClient("insights", "{");
        InsightsRequest request = fullInsightsRequest();

        thrown.expect(MinFraudException.class);
        thrown.expectMessage("Received a 200 response but could not decode it as JSON");
        client.insights(request);
    }

    @Test
    public void testInsufficientCredit() throws Exception {
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("out of credit");
        createInsightsError(
                402,
                "application/json",
                "{\"code\":\"INSUFFICIENT_FUNDS\",\"error\":\"out of credit\"}"
        );
    }

    @Test
    @Parameters({"AUTHORIZATION_INVALID",
            "LICENSE_KEY_REQUIRED",
            "USER_ID_REQUIRED"})
    public void testInvalidAuth(String code) throws Exception {
        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Invalid auth");
        createInsightsError(
                401,
                "application/json",
                "{\"code\":\"" + code + "\",\"error\":\"Invalid auth\"}"
        );
    }

    @Test
    public void testInvalidRequest() throws Exception {
        thrown.expect(InvalidRequestException.class);
        thrown.expectMessage("IP invalid");
        createInsightsError(
                400,
                "application/json",
                "{\"code\":\"IP_ADDRESS_INVALID\",\"error\":\"IP invalid\"}"
        );
    }

    @Test
    public void test400WithInvalidJson() throws Exception {
        thrown.expect(HttpException.class);
        thrown.expectMessage(matchesPattern("Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body: \\{blah\\}"));
        createInsightsError(
                400,
                "application/json",
                "{blah}"
        );
    }

    @Test
    public void test400WithNoBody() throws Exception {
        thrown.expect(HttpException.class);
        thrown.expectMessage(matchesPattern("Received a 400 error for .*/minfraud/v2.0/insights with no body"));
        createInsightsError(
                400,
                "application/json",
                ""
        );
    }

    @Test
    public void test400WithUnexpectedContentType() throws Exception {
        thrown.expect(HttpException.class);
        thrown.expectMessage(matchesPattern("Received a 400 error for .*/minfraud/v2.0/insights but it did not include the expected JSON body: text"));
        createInsightsError(
                400,
                "text/plain",
                "text"
        );
    }

    @Test
    public void test400WithUnexpectedJson() throws Exception {
        thrown.expect(HttpException.class);
        thrown.expectMessage("Error response contains JSON but it does not specify code or error keys: {\"not\":\"expected\"}");
        createInsightsError(
                400,
                "application/json",
                "{\"not\":\"expected\"}"
        );
    }

    @Test
    public void test300() throws Exception {
        thrown.expect(HttpException.class);
        thrown.expectMessage(startsWith("Received an unexpected HTTP status (300)"));
        createInsightsError(
                300,
                "application/json",
                ""
        );
    }

    @Test
    public void test500() throws Exception {
        thrown.expect(HttpException.class);
        thrown.expectMessage(startsWith("Received a server error (500)"));
        createInsightsError(
                500,
                "application/json",
                ""
        );
    }

    private WebServiceClient createSuccessClient(String service, String responseContent) throws IOException, MinFraudException {

        return createClient(
                service,
                200,
                "application/vnd.maxmind.com-minfraud-" + service + "+json; charset=UTF-8; version=2.0\n",
                responseContent
        );
    }

    private void createInsightsError(int status, String contentType, String responseContent) throws Exception {
        WebServiceClient client = createClient(
                "insights",
                status,
                contentType,
                responseContent
        );
        client.insights(fullInsightsRequest());
    }

    private WebServiceClient createClient(String service, int status, String contentType, String responseContent) {
        stubFor(post(urlEqualTo("/minfraud/v2.0/" + service))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", contentType)
                                // This is wrong if we use non-ASCII characters, but we don't currently
                        .withHeader("Content-Length", Integer.toString(responseContent.length()))
                        .withBody(responseContent)));

        return new WebServiceClient.Builder(6, "0123456789")
                .host("localhost")
                .port(this.wireMockRule.port())
                .disableHttps()
                .build();
    }
}
