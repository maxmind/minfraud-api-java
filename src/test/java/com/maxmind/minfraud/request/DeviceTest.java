package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.maxmind.minfraud.request.Device.Builder;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

public class DeviceTest {

    private final InetAddress ip;

    public DeviceTest() throws UnknownHostException {
        ip = InetAddress.getByName("1.1.1.1");
    }

    @Test
    public void testConstructorWithoutIP() {
        Device device = new Builder().build();
        assertNull(device.ipAddress());
    }

    @Test
    public void testIpAddressThroughConstructor() {
        Device device = new Builder(ip).build();
        assertEquals(ip, device.ipAddress());
    }

    @Test
    public void testIpAddress() {
        Device device = new Builder().ipAddress(ip).build();
        assertEquals(ip, device.ipAddress());
    }

    @Test
    public void testUserAgent() {
        String ua = "Mozila 5";
        Device device = new Builder(ip).userAgent(ua).build();
        assertEquals(ua, device.userAgent());
    }

    @Test
    public void testAcceptLanguage() {
        String al = "en-US";
        Device device = new Builder(ip).acceptLanguage(al).build();
        assertEquals(al, device.acceptLanguage());
    }

    @Test
    public void testSessionAge() {
        Double hour = 3600d;
        Device device = new Builder(ip).sessionAge(hour).build();
        assertEquals(hour, device.sessionAge());
    }

    @Test
    public void testSessionId() {
        String id = "foobar";
        Device device = new Builder(ip).sessionId(id).build();
        assertEquals(id, device.sessionId());
    }
}
