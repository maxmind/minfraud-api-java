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
                        .put("id", "C8D3BE1A-BE26-11E5-8C50-1B575C37265F")
                        .end()
                        .finish()
        );

        assertEquals(UUID.fromString("C8D3BE1A-BE26-11E5-8C50-1B575C37265F"), device.getId());
    }
}
