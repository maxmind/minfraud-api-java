package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class DeviceTest extends AbstractOutputTest {

    @Test
    public void testDevice() throws Exception {
        Device device = this.deserialize(
                Device.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("confidence", 99)
                        .put("id", "C8D3BE1A-BE26-11E5-8C50-1B575C37265F")
                        .put("last_seen", "2016-06-08T14:16:38Z")
                        .put("local_time", "2018-04-05T15:21:01-07:00")
                        .end()
                        .finish()
        );

        assertEquals(99.0, device.getConfidence(), 1e-15);
        assertEquals(UUID.fromString("C8D3BE1A-BE26-11E5-8C50-1B575C37265F"), device.getId());
        assertEquals("2016-06-08T14:16:38Z", device.getLastSeen());
        assertEquals("2016-06-08T14:16:38Z", device.getLastSeenDateTime().toString());
        assertEquals("2018-04-05T15:21:01-07:00", device.getLocalTime());
        assertEquals("2018-04-05T15:21:01-07:00", device.getLocalDateTime().toString());

    }
}
