package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class ShippingAddressTest extends AbstractOutputTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testShippingAddress() throws Exception {
        ShippingAddress address = this.deserialize(
            ShippingAddress.class,
            JSON.std
                .composeString()
                .startObject()
                .put("is_in_ip_country", true)
                .put("latitude", 43.1)
                .put("longitude", 32.1)
                .put("distance_to_ip_location", 100)
                .put("is_postal_in_city", true)
                .put("is_high_risk", false)
                .put("distance_to_billing_address", 200)
                .end()
                .finish()
        );

        assertTrue(address.isInIpCountry(), "correct isInIpCountry");
        assertTrue(address.isPostalInCity(), "correct isPostalInCity");
        assertEquals(
            100,
            address.getDistanceToIpLocation().longValue(),
            "correct getDistanceToIpLocation"
        );
        assertEquals(
            32.1,
            address.getLongitude(),
            DELTA,
            "correct longitude"
        );
        assertEquals(
            43.1,
            address.getLatitude(),
            DELTA,
            "correct latitude"
        );

        assertFalse(address.isHighRisk(), "is high risk");
        assertEquals(
            Integer.valueOf(200),
            address.getDistanceToBillingAddress(),
            "distance to billing address"
        );
    }
}
