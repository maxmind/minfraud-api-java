package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

public class InsightsTest extends AbstractOutputTest {

    @Test
    public void testInsights() throws Exception {
        String id = "b643d445-18b2-4b9d-bad4-c9c4366e402a";
        Insights insights = deserialize(
                Insights.class,
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

        assertEquals( "US", insights.getIpLocation().getCountry().getIsoCode());
        assertTrue(insights.getCreditCard().isPrepaid());
        assertTrue(insights.getShippingAddress().isInIpCountry());
        assertTrue(insights.getBillingAddress().isInIpCountry());
        assertEquals(id, insights.getId());
        assertEquals(Integer.valueOf(123), insights.getCreditsRemaining());
        assertEquals(Double.valueOf(0.01), insights.getRiskScore());
        assertEquals("INVALID_INPUT", insights.getWarnings().get(0).getCode());
    }
}