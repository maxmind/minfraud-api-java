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

        assertTrue(factors.shippingPhone().isVoip(), "correct shipping phone isVoip");
        assertFalse(factors.shippingPhone().matchesPostal(), "correct shipping phone matchesPostal");
        assertFalse(factors.billingPhone().isVoip(), "correct billing phone isVoip");
        assertTrue(factors.billingPhone().matchesPostal(), "correct billing phone matchesPostal");

        assertEquals(
            Double.valueOf(1.20),
            factors.fundsRemaining(),
            "correct funnds remaining"
        );
        assertEquals(UUID.fromString(id), factors.id(), "correct ID");
        assertEquals(
            Integer.valueOf(123),
            factors.queriesRemaining(),
            "correct queries remaining"
        );
        assertEquals(
            Double.valueOf(0.01),
            factors.riskScore(),
            "correct risk score"
        );
        assertEquals(1, factors.riskScoreReasons().size());
        assertEquals(
            Double.valueOf(45),
            factors.riskScoreReasons().get(0).multiplier(),
            "risk multiplier"
        );
        assertEquals(1, factors.riskScoreReasons().get(0).reasons().size());
        assertEquals(
            "ANONYMOUS_IP",
            factors.riskScoreReasons().get(0).reasons().get(0).code(),
            "risk reason code"
        );
        assertEquals(
            "Risk due to IP being an Anonymous IP",
            factors.riskScoreReasons().get(0).reasons().get(0).reason(),
            "risk reason"
        );
    }
}
