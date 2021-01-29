package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Device.Builder;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeviceTest {

    private final InetAddress ip;

    public DeviceTest() throws UnknownHostException {
        ip = InetAddress.getByName("1.1.1.1");
    }

    @Test
    public void testConstructorWithoutIP() {
        Device device = new Builder().build();
        assertNull(device.getIpAddress());
    }

    @Test
    public void testIpAddressThroughConstructor() {
        Device device = new Builder(ip).build();
        assertEquals(ip, device.getIpAddress());
    }

    @Test
    public void testIpAddress() {
        Device device = new Builder().ipAddress(ip).build();
        assertEquals(ip, device.getIpAddress());
    }

    @Test
    public void testUserAgent() {
        String ua = "Mozila 5";
        Device device = new Builder(ip).userAgent(ua).build();
        assertEquals(ua, device.getUserAgent());
    }

    @Test
    public void testAcceptLanguage() {
        String al = "en-US";
        Device device = new Builder(ip).acceptLanguage(al).build();
        assertEquals(al, device.getAcceptLanguage());
    }

    @Test
    public void testSessionAge() {
        Double hour = 3600d;
        Device device = new Builder(ip).sessionAge(hour).build();
        assertEquals(hour, device.getSessionAge());
    }

    @Test
    public void testSessionId() {
        String id = "foobar";
        Device device = new Builder(ip).sessionId(id).build();
        assertEquals(id, device.getSessionId());
    }
}
