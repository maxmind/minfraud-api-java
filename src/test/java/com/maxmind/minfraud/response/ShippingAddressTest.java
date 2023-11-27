package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class ShippingAddressTest extends AbstractAddressTest {

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

        this.testAddress(address);

        assertFalse(address.isHighRisk(), "is high risk");
        assertEquals(
            Integer.valueOf(200),
            address.getDistanceToBillingAddress(),
            "distance to billing address"
        );
    }
}
