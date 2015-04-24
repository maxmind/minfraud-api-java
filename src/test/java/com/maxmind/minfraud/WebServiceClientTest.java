package com.maxmind.minfraud;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.maxmind.minfraud.exception.MinFraudException;
import com.maxmind.minfraud.request.*;
import com.maxmind.minfraud.response.InsightsResponse;
import com.maxmind.minfraud.response.ScoreResponse;
import org.junit.Rule;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.maxmind.minfraud.request.RequestTestHelper.*;

public class WebServiceClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(0); // 0 picks random port

    @Test
    public void testFullScoreRequest() throws Exception {
        String responseContent = readJsonFile("score-response");
        WebServiceClient client = this.createClient("score", responseContent);
        ScoreRequest request = fullScoreRequest();
        ScoreResponse response = client.score(request);

        JSONAssert.assertEquals(responseContent, response.toJson(), true);
        verifyRequestFor("score");
    }

    @Test
    public void testFullInsightsRequest() throws Exception {
        String responseContent = readJsonFile("insights-response");
        WebServiceClient client = this.createClient("insights", responseContent);
        InsightsRequest request = fullInsightsRequest();
        InsightsResponse response = client.insights(request);

        // We use non-strict checking as there is some extra stuff in the serialized
        // object, most notably the "name" field in the GeoIP2 InsightsResponse subobjects.
        // We cannot change this as it would be a breaking change to the GeoIP2 API.
        JSONAssert.assertEquals(responseContent, response.toJson(), false);
        verifyRequestFor("insights");
    }



    public WebServiceClient createClient(String service, String responseContent) throws IOException, MinFraudException {

        stubFor(post(urlEqualTo("/minfraud/v2.0/" + service))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", " application/vnd.maxmind.com-minfraud-" + service + "+json; charset=UTF-8; version=2.0\n")
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
