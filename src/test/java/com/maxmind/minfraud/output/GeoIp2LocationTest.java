package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeoIp2LocationTest extends AbstractOutputTest {

    @Test
    public void testGetLocalTime() throws Exception {
        String time = "2015-04-19T12:59:23-01:00";
        GeoIp2Location location = deserialize(
                GeoIp2Location.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("local_time", time)
                        .end()
                        .finish()
        );

        assertEquals(location.getLocalTime(), time);
    }
}