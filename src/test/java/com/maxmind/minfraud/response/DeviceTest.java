package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.jr.ob.JSON;
import java.util.UUID;
import org.junit.jupiter.api.Test;

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

        assertEquals(99.0, device.confidence(), 1e-15);
        assertEquals(UUID.fromString("C8D3BE1A-BE26-11E5-8C50-1B575C37265F"), device.id());
        assertEquals("2016-06-08T14:16:38Z", device.lastSeen());
        assertEquals("2016-06-08T14:16:38Z", device.getLastSeenDateTime().toString());
        assertEquals("2018-04-05T15:21:01-07:00", device.localTime());
        assertEquals("2018-04-05T15:21:01-07:00", device.getLocalDateTime().toString());

    }
}
