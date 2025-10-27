package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.jr.ob.JSON;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ScoreResponseTest extends AbstractOutputTest {

    @Test
    public void testScore() throws Exception {
        String id = "b643d445-18b2-4b9d-bad4-c9c4366e402a";
        ScoreResponse score = this.deserialize(
            ScoreResponse.class,
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

        assertEquals(UUID.fromString(id), score.id(), "correct ID");
        assertEquals(Double.valueOf(1.20), score.fundsRemaining(), "correct funds remaining");
        assertEquals(Integer.valueOf(123), score.queriesRemaining(), "queries remaining");
        assertEquals(Double.valueOf(0.01), score.riskScore(), "risk score");
        assertEquals("manual_review", score.disposition().action(), "disposition");
        assertEquals(Double.valueOf(0.02), score.ipAddress().risk(), "IP risk");
        assertEquals("INVALID_INPUT", score.warnings().get(0).code(), "warning code");
    }
}
