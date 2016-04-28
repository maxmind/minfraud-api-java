package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ScoreResponseTest extends AbstractOutputTest {

    @Test
    public void testScore() throws Exception {
        String id = "b643d445-18b2-4b9d-bad4-c9c4366e402a";
        ScoreResponse score = this.deserialize(
                InsightsResponse.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("credits_remaining", 123)
                        .put("id", id)
                        .put("risk_score", 0.01)
                        .startObjectField("ip_address")
                        .put("risk", 0.02)
                        .end()
                        .startArrayField("warnings")
                        .startObject()
                        .put("code", "INVALID_INPUT")
                        .end()
                        .end()
                        .end()
                        .finish()
        );

        assertEquals("correct ID", UUID.fromString(id), score.getId());
        assertEquals("credits remaining", Integer.valueOf(123), score.getCreditsRemaining());
        assertEquals("risk score", Double.valueOf(0.01), score.getRiskScore());
        assertEquals("IP ris", Double.valueOf(0.02), score.getIpAddress().getRisk());
        assertEquals("warning code", "INVALID_INPUT", score.getWarnings().get(0).getCode());
    }
}
