package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class BillingAddressTest extends AbstractOutputTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testBillingAddress() throws Exception {
        BillingAddress address = this.deserialize(BillingAddress.class,
            JSON.std
                .composeString()
                .startObject()
                .put("is_in_ip_country", true)
                .put("latitude", 43.1)
                .put("longitude", 32.1)
                .put("distance_to_ip_location", 100)
                .put("is_postal_in_city", true)
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
    }
}