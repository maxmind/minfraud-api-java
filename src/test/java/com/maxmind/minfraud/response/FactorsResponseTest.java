package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class FactorsResponseTest extends AbstractOutputTest {

    @Test
    public void testFactors() throws Exception {
        String id = "b643d445-18b2-4b9d-bad4-c9c4366e402a";
        FactorsResponse factors = this.deserialize(
                FactorsResponse.class,
                JSON.std
                        .composeString()
                        .startObject()
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
                        .end()
                        .finish()
        );

        assertEquals("avs_result", Double.valueOf(0.01), factors.getSubscores().getAvsResult());
        assertEquals("billing_address", Double.valueOf(0.02), factors.getSubscores().getBillingAddress());
        assertEquals("billing_address_distance_to_ip_location", Double.valueOf(0.03), factors.getSubscores().getBillingAddressDistanceToIpLocation());
        assertEquals("browser", Double.valueOf(0.04), factors.getSubscores().getBrowser());
        assertEquals("chargeback", Double.valueOf(0.05), factors.getSubscores().getChargeback());
        assertEquals("country", Double.valueOf(0.06), factors.getSubscores().getCountry());
        assertEquals("country_mismatch", Double.valueOf(0.07), factors.getSubscores().getCountryMismatch());
        assertEquals("cvv_result", Double.valueOf(0.08), factors.getSubscores().getCvvResult());
        assertEquals("device", Double.valueOf(0.18), factors.getSubscores().getDevice());
        assertEquals("email_address", Double.valueOf(0.09), factors.getSubscores().getEmailAddress());
        assertEquals("email_domain", Double.valueOf(0.10), factors.getSubscores().getEmailDomain());
        assertEquals("email_local_part", Double.valueOf(0.19), factors.getSubscores().getEmailLocalPart());
        assertEquals("issuer_id_number", Double.valueOf(0.13), factors.getSubscores().getIssuerIdNumber());
        assertEquals("order_amount", Double.valueOf(0.14), factors.getSubscores().getOrderAmount());
        assertEquals("phone_number", Double.valueOf(0.15), factors.getSubscores().getPhoneNumber());
        assertEquals("shipping_address", Double.valueOf(0.2), factors.getSubscores().getShippingAddress());
        assertEquals("shipping_address_distance_to_ip_location", Double.valueOf(0.16), factors.getSubscores().getShippingAddressDistanceToIpLocation());
        assertEquals("time_of_day", Double.valueOf(0.17), factors.getSubscores().getTimeOfDay());
        assertEquals("correct funnds remaining", Double.valueOf(1.20), factors.getFundsRemaining());
        assertEquals("correct ID", UUID.fromString(id), factors.getId());
        assertEquals("correct queries remaining", Integer.valueOf(123), factors.getQueriesRemaining());
        assertEquals("correct risk score", Double.valueOf(0.01), factors.getRiskScore());
    }
}
