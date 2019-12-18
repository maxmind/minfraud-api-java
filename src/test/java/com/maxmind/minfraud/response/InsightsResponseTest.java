package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import java.util.UUID;

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
                        .startObjectField("disposition")
                        .put("action", "accept")
                        .end()
                        .startObjectField("ip_address")
                        .startObjectField("country")
                        .put("iso_code", "US")
                        .end()
                        .startObjectField("traits")
                        .put("ip_address", "81.2.69.160")
                        .put("network", "81.2.69.0/24")
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

        assertEquals("disposition", "accept", insights.getDisposition().getAction());
        assertEquals("correct country ISO", "US", insights.getIpAddress().getCountry().getIsoCode());
        assertTrue("correct credit card prepaid", insights.getCreditCard().isPrepaid());
        assertTrue("correct shipping address is in IP country", insights.getShippingAddress().isInIpCountry());
        assertTrue("correct billing address is in IP country", insights.getBillingAddress().isInIpCountry());
        assertEquals("correct funds remaining", Double.valueOf(1.20), insights.getFundsRemaining());
        assertEquals("correct ID", UUID.fromString(id), insights.getId());
        assertEquals("correct queries remaining", Integer.valueOf(123), insights.getQueriesRemaining());
        assertEquals("correct risk score", Double.valueOf(0.01), insights.getRiskScore());
        assertEquals("correct warning code", "INVALID_INPUT", insights.getWarnings().get(0).getCode());
        assertEquals("81.2.69.160", insights.getIpAddress().getTraits().getIpAddress());
        assertEquals("81.2.69.0/24", insights.getIpAddress().getTraits().getNetwork().toString());
    }
}
