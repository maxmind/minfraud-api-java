package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InsightsResponseTest extends AbstractOutputTest {

    @Test
    public void testInsights() throws Exception {
        String id = "b643d445-18b2-4b9d-bad4-c9c4366e402a";
        InsightsResponse insights = this.deserialize(
                InsightsResponse.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .startObjectField("ip_location")
                        .startObjectField("country")
                        .put("iso_code", "US")
                        .end()
                        .end()
                        .startObjectField("credit_card")
                        .put("is_prepaid", true)
                        .end()
                        .startObjectField("shipping_address")
                        .put("is_in_ip_country", true)
                        .end()
                        .startObjectField("billing_address")
                        .put("is_in_ip_country", true)
                        .end()
                        .put("credits_remaining", 123)
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

        assertEquals("correct country ISO", "US", insights.getIpLocation().getCountry().getIsoCode());
        assertTrue("correct credit card prepaid", insights.getCreditCard().isPrepaid());
        assertTrue("correct shipping address is in IP country", insights.getShippingAddress().isInIpCountry());
        assertTrue("correct billing address is in IP country", insights.getBillingAddress().isInIpCountry());
        assertEquals("correct ID", id, insights.getId());
        assertEquals("correct credits remaining", Integer.valueOf(123), insights.getCreditsRemaining());
        assertEquals("correct risk score", Double.valueOf(0.01), insights.getRiskScore());
        assertEquals("correct warning code", "INVALID_INPUT", insights.getWarnings().get(0).getCode());
    }
}