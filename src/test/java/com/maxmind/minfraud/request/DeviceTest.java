package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Device.Builder;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class DeviceTest {

    private InetAddress ip;

    public DeviceTest() throws UnknownHostException {
        ip = InetAddress.getByName("1.1.1.1");
    }

    @Test
    public void testIpAddress() throws Exception {
        Device device = new Builder(ip).build();
        assertEquals(ip, device.getIpAddress());
    }

    @Test
    public void testUserAgent() throws Exception {
        String ua = "Mozila 5";
        Device device = new Builder(ip).userAgent(ua).build();
        assertEquals(ua, device.getUserAgent());
    }

    @Test
    public void testAcceptLanguage() throws Exception {
        String al = "en-US";
        Device device = new Builder(ip).acceptLanguage(al).build();
        assertEquals(al, device.getAcceptLanguage());
    }


    @Test
    public void testSessionAge() throws Exception {
        Double hour = 3600d;
        Device device = new Builder(ip).sessionAge(hour).build();
        assertEquals(hour, device.getSessionAge());
    }

    @Test
    public void testSessionId() throws Exception {
        String id = "foobar";
        Device device = new Builder(ip).sessionId(id).build();
        assertEquals(id, device.getSessionId());
    }
}
