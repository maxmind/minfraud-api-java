package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

public class IpLocationTest extends AbstractOutputTest {

    @Test
    public void testIpLocation() throws Exception {
        String time = "2015-04-19T12:59:23-01:00";

        IpLocation loc = deserialize(
                IpLocation.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .startObjectField("country")
                        .put("is_high_risk", true)
                        .end()
                        .startObjectField("location")
                        .put("local_time", time)
                        .end()
                        .end()
                        .finish()
        );

        assertEquals(time, loc.getLocation().getLocalTime());
        assertTrue(loc.getCountry().isHighRisk());
    }

}