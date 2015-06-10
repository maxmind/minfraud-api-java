package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IpAddressTest extends AbstractOutputTest {

    @Test
    public void testIpAddress() throws Exception {
        String time = "2015-04-19T12:59:23-01:00";

        IpAddress address = this.deserialize(
                IpAddress.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("risk", 99)
                        .startObjectField("country")
                        .put("is_high_risk", true)
                        .end()
                        .startObjectField("location")
                        .put("local_time", time)
                        .end()
                        .end()
                        .finish()
        );

        assertEquals("IP risk", new Double(99), address.getRisk());
        assertEquals("correct local time", time, address.getLocation().getLocalTime());
        assertTrue("isHighRisk", address.getCountry().isHighRisk());
    }
}