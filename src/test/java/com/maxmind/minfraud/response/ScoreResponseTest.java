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
                        .put("funds_remaining", 1.20)
                        .put("id", id)
                        .put("queries_remaining", 123)
                        .put("risk_score", 0.01)
                        .startObjectField("disposition")
                        .put("action", "manual_review")
                        .end()
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
        assertEquals("correct funds remaining", Double.valueOf(1.20), score.getFundsRemaining());
        assertEquals("queries remaining", Integer.valueOf(123), score.getQueriesRemaining());
        assertEquals("risk score", Double.valueOf(0.01), score.getRiskScore());
        assertEquals("disposition", "manual_review", score.getDisposition().getAction());
        assertEquals("IP risk", Double.valueOf(0.02), score.getIpAddress().getRisk());
        assertEquals("warning code", "INVALID_INPUT", score.getWarnings().get(0).getCode());
    }
}
