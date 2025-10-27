package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class RiskScoreReasonTest extends AbstractOutputTest {

    @Test
    public void testRiskScoreReason() throws Exception {
        RiskScoreReason reason = this.deserialize(
            RiskScoreReason.class,
            JSON.std
                .composeString()
                .startObject()
                .put("multiplier", 45)
                .startArrayField("reasons")
                .startObject()
                .put("code", "ANONYMOUS_IP")
                .put("reason", "Risk due to IP being an Anonymous IP")
                .end()
                .end()
                .end()
                .finish()
        );

        assertEquals(Double.valueOf(45), reason.multiplier(), "multiplier");
        assertEquals(1, reason.reasons().size());
        assertEquals(
            "ANONYMOUS_IP",
            reason.reasons().get(0).code(),
            "risk reason code"
        );
        assertEquals(
            "Risk due to IP being an Anonymous IP",
            reason.reasons().get(0).reason(),
            "risk reason"
        );
    }

    @Test
    public void testEmptyObject() throws Exception {
        RiskScoreReason reason = this.deserialize(
            RiskScoreReason.class,
            "{}"
        );

        assertNotNull(reason.reasons());
    }
}
