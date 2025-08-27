package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class FactorsResponseTest extends AbstractOutputTest {

    @Test
    public void testFactors() throws Exception {
        String id = "b643d445-18b2-4b9d-bad4-c9c4366e402a";
        FactorsResponse factors = this.deserialize(
            FactorsResponse.class,
            JSON.std
                .composeString()
                .startObject()
                .startObjectField("billing_phone")
                .put("is_voip", false)
                .put("matches_postal", true)
                .end()
                .startObjectField("shipping_phone")
                .put("is_voip", true)
                .put("matches_postal", false)
                .end()
                .put("funds_remaining", 1.20)
                .put("queries_remaining", 123)
                .put("id", id)
                .put("risk_score", 0.01)
                .startArrayField("risk_score_reasons")
                .startObject()
                .put("multiplier", 45)
                .startArrayField("reasons")
                .startObject()
                .put("code", "ANONYMOUS_IP")
                .put("reason", "Risk due to IP being an Anonymous IP")
                .end()
                .end()
                .end()
                .end()
                .end()
                .finish()
        );

        assertTrue(factors.getShippingPhone().isVoip(), "correct shipping phone isVoip");
        assertFalse(factors.getShippingPhone().matchesPostal(), "correct shipping phone matchesPostal");
        assertFalse(factors.getBillingPhone().isVoip(), "correct billing phone isVoip");
        assertTrue(factors.getBillingPhone().matchesPostal(), "correct billing phone matchesPostal");

        assertEquals(
            Double.valueOf(1.20),
            factors.getFundsRemaining(),
            "correct funnds remaining"
        );
        assertEquals(UUID.fromString(id), factors.getId(), "correct ID");
        assertEquals(
            Integer.valueOf(123),
            factors.getQueriesRemaining(),
            "correct queries remaining"
        );
        assertEquals(
            Double.valueOf(0.01),
            factors.getRiskScore(),
            "correct risk score"
        );
        assertEquals(1, factors.getRiskScoreReasons().size());
        assertEquals(
            Double.valueOf(45),
            factors.getRiskScoreReasons().get(0).getMultiplier(),
            "risk multiplier"
        );
        assertEquals(1, factors.getRiskScoreReasons().get(0).getReasons().size());
        assertEquals(
            "ANONYMOUS_IP",
            factors.getRiskScoreReasons().get(0).getReasons().get(0).getCode(),
            "risk reason code"
        );
        assertEquals(
            "Risk due to IP being an Anonymous IP",
            factors.getRiskScoreReasons().get(0).getReasons().get(0).getReason(),
            "risk reason"
        );
    }
}
