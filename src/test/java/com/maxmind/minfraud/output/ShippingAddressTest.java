package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import com.maxmind.minfraud.input.Shipping;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShippingAddressTest extends AbstractAddressTest {

    @Test
    public void testShippingAddress() throws Exception {
        ShippingAddress address = deserialize(
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

        testAddress(address);

        assertFalse(address.isHighRisk());
        assertEquals(Integer.valueOf(200), address.getDistanceToBillingAddress());
    }
}
