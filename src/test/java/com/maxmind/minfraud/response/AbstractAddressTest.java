package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractAddressTest extends AbstractOutputTest {
    private static final double DELTA = 1e-15;

    void testAddress(AbstractAddress address) {
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
            AbstractAddressTest.DELTA,
            "correct longitude"
        );
        assertEquals(
            43.1,
            address.getLatitude(),
            AbstractAddressTest.DELTA,
            "correct latitude"
        );
    }
}
