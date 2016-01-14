package com.maxmind.minfraud.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

abstract class AbstractAddressTest extends AbstractOutputTest {
    private static final double DELTA = 1e-15;

    void testAddress(AbstractAddress address) {
        assertTrue("correct isInIpCountry", address.isInIpCountry());
        assertTrue("correct isPostalInCity", address.isPostalInCity());
        assertEquals("correct getDistanceToIpLocation", 100, address.getDistanceToIpLocation().longValue());
        assertEquals("correct longitude", 32.1, address.getLongitude(), AbstractAddressTest.DELTA);
        assertEquals("correct latitude", 43.1, address.getLatitude(), AbstractAddressTest.DELTA);
    }
}
