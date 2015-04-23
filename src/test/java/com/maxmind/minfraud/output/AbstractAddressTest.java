package com.maxmind.minfraud.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

abstract class AbstractAddressTest extends AbstractOutputTest {
    private static final double DELTA = 1e-15;

    public void testAddress(Address address) throws Exception {
        assertTrue(address.isInIpCountry());
        assertTrue(address.isPostalInCity());
        assertEquals(100, address.getDistanceToIpLocation().longValue());
        assertEquals(32.1, address.getLongitude(), DELTA);
        assertEquals(43.1, address.getLatitude(), DELTA);
    }
}