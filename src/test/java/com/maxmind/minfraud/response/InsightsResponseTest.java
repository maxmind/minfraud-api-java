package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class InsightsResponseTest extends AbstractOutputTest {

    @Test
    public void testInsights() throws Exception {
        String id = "b643d445-18b2-4b9d-bad4-c9c4366e402a";
        InsightsResponse insights = this.deserialize(
            InsightsResponse.class,
            JSON.std
                .composeString()
                .startObject()
                .startObjectField("disposition")
                .put("action", "accept")
                .end()
                .startObjectField("email")
                .startObjectField("domain")
                .put("first_seen", "2014-02-03")
                .end()
                .end()
                .startObjectField("ip_address")
                .startObjectField("country")
                .put("iso_code", "US")
                .end()
                .startObjectField("traits")
                .put("ip_address", "152.216.7.110")
                .put("network", "81.2.69.0/24")
                .end()
                .end()
                .startObjectField("credit_card")
                .put("is_business", true)
                .put("is_prepaid", true)
                .end()
                .startObjectField("shipping_address")
                .put("is_in_ip_country", true)
                .end()
                .startObjectField("shipping_phone")
                .put("is_voip", true)
                .put("matches_postal", false)
                .end()
                .startObjectField("billing_address")
                .put("is_in_ip_country", true)
                .end()
                .startObjectField("billing_phone")
                .put("is_voip", false)
                .put("matches_postal", true)
                .end()
                .put("funds_remaining", 1.20)
                .put("queries_remaining", 123)
                .put("id", id)
                .put("risk_score", 0.01)
                .startArrayField("warnings")
                .startObject()
                .put("code", "INVALID_INPUT")
                .end()
                .end()
                .end()
                .finish()
        );

        assertEquals("accept", insights.disposition().action(), "disposition");
        assertEquals(
            LocalDate.parse("2014-02-03"),
            insights.email().domain().firstSeen(),
            "email domain first seen"
        );
        assertEquals(
            "US",
            insights.ipAddress().country().isoCode(),
            "correct country ISO"
        );
        assertTrue(insights.creditCard().isBusiness(), "correct credit card is business");
        assertTrue(insights.creditCard().isPrepaid(), "correct credit card prepaid");

        assertTrue(
            insights.shippingAddress().isInIpCountry(),
            "correct shipping address is in IP country"
        );
        assertTrue(insights.shippingPhone().isVoip(), "correct shipping phone isVoip");
        assertFalse(
            insights.shippingPhone().matchesPostal(),
            "correct shipping phone matchesPostal"
        );

        assertTrue(
            insights.billingAddress().isInIpCountry(),
            "correct billing address is in IP country"
        );
        assertFalse(insights.billingPhone().isVoip(), "correct billing phone isVoip");
        assertTrue(
            insights.billingPhone().matchesPostal(),
            "correct billing phone matchesPostal"
        );

        assertEquals(
            Double.valueOf(1.20),
            insights.fundsRemaining(),
            "correct funds remaining"
        );
        assertEquals(UUID.fromString(id), insights.id(), "correct ID");
        assertEquals(
            Integer.valueOf(123),
            insights.queriesRemaining(),
            "correct queries remaining"
        );
        assertEquals(Double.valueOf(0.01), insights.riskScore(), "correct risk score");
        assertEquals(
            "INVALID_INPUT",
            insights.warnings().get(0).code(),
            "correct warning code"
        );
        assertEquals("152.216.7.110", insights.ipAddress().traits().ipAddress().getHostAddress());
        assertEquals("81.2.69.0/24", insights.ipAddress().traits().network().toString());
    }
}
