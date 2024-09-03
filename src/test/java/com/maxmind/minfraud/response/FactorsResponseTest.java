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
                .end()
                .startObjectField("shipping_phone")
                .put("is_voip", true)
                .end()
                .startObjectField("subscores")
                .put("avs_result", 0.01)
                .put("billing_address", 0.02)
                .put("billing_address_distance_to_ip_location", 0.03)
                .put("browser", 0.04)
                .put("chargeback", 0.05)
                .put("country", 0.06)
                .put("country_mismatch", 0.07)
                .put("cvv_result", 0.08)
                .put("device", 0.18)
                .put("email_address", 0.09)
                .put("email_domain", 0.10)
                .put("email_local_part", 0.19)
                .put("email_tenure", 0.11)
                .put("ip_tenure", 0.12)
                .put("issuer_id_number", 0.13)
                .put("order_amount", 0.14)
                .put("phone_number", 0.15)
                .put("shipping_address", 0.2)
                .put("shipping_address_distance_to_ip_location", 0.16)
                .put("time_of_day", 0.17)
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
        assertFalse(factors.getBillingPhone().isVoip(), "correct billing phone isVoip");

        assertEquals(
            Double.valueOf(0.01),
            factors.getSubscores().getAvsResult(),
            "avs_result"
        );
        assertEquals(
            Double.valueOf(0.02),
            factors.getSubscores().getBillingAddress(),
            "billing_address"
        );
        assertEquals(
            Double.valueOf(0.03),
            factors.getSubscores().getBillingAddressDistanceToIpLocation(),
            "billing_address_distance_to_ip_location"
        );
        assertEquals(Double.valueOf(0.04), factors.getSubscores().getBrowser(), "browser");
        assertEquals(Double.valueOf(0.05), factors.getSubscores().getChargeback(), "chargeback");
        assertEquals(Double.valueOf(0.06), factors.getSubscores().getCountry(), "country");
        assertEquals(
            Double.valueOf(0.07),
            factors.getSubscores().getCountryMismatch(),
            "country_mismatch"
        );
        assertEquals(
            Double.valueOf(0.08),
            factors.getSubscores().getCvvResult(),
            "cvv_result"
        );
        assertEquals(Double.valueOf(0.18), factors.getSubscores().getDevice(), "device");
        assertEquals(
            Double.valueOf(0.09),
            factors.getSubscores().getEmailAddress(),
            "email_address"
        );
        assertEquals(
            Double.valueOf(0.10),
            factors.getSubscores().getEmailDomain(),
            "email_domain"
        );
        assertEquals(
            Double.valueOf(0.19),
            factors.getSubscores().getEmailLocalPart(),
            "email_local_part"
        );
        assertEquals(
            Double.valueOf(0.13),
            factors.getSubscores().getIssuerIdNumber(),
            "issuer_id_number"
        );
        assertEquals(
            Double.valueOf(0.14),
            factors.getSubscores().getOrderAmount(),
            "order_amount"
        );
        assertEquals(
            Double.valueOf(0.15),
            factors.getSubscores().getPhoneNumber(),
            "phone_number"
        );
        assertEquals(
            Double.valueOf(0.2),
            factors.getSubscores().getShippingAddress(),
            "shipping_address"
        );
        assertEquals(
            Double.valueOf(0.16),
            factors.getSubscores().getShippingAddressDistanceToIpLocation(),
            "shipping_address_distance_to_ip_location"
        );
        assertEquals(
            Double.valueOf(0.17),
            factors.getSubscores().getTimeOfDay(),
            "time_of_day"
        );
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
