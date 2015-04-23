package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillingAddressTest extends AbstractAddressTest {

    @Test
    public void testBillingAddress() throws Exception {
        BillingAddress address = deserialize(BillingAddress.class,
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

        testAddress(address);
    }
}