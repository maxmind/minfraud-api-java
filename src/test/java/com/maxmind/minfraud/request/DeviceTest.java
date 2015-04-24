package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Device.Builder;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class DeviceTest {

    @Test
    public void testIpAddress() throws Exception {
        InetAddress ip = InetAddress.getByName("1.1.1.1");
        Device device = new Builder().ipAddress(ip).build();
        assertEquals(ip, device.getIpAddress());
    }

    @Test
    public void testUserAgent() throws Exception {
        String ua = "Mozila 5";
        Device device = new Builder().userAgent(ua).build();
        assertEquals(ua, device.getUserAgent());
    }

    @Test
    public void testAcceptLanguage() throws Exception {
        String al = "en-US";
        Device device = new Builder().acceptLanguage(al).build();
        assertEquals(al, device.getAcceptLanguage());
    }
}