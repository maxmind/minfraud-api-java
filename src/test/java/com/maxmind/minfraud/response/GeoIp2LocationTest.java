package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class GeoIp2LocationTest extends AbstractOutputTest {

    @Test
    public void testGetLocalTime() throws Exception {
        String time = "2015-04-19T12:59:23-01:00";
        GeoIp2Location location = this.deserialize(
            GeoIp2Location.class,
            JSON.std
                .composeString()
                .startObject()
                .put("local_time", time)
                .end()
                .finish()
        );

        assertEquals(time, location.localTime());
        assertEquals(time, location.getLocalDateTime().toString());
    }
}