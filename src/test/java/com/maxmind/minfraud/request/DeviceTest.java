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
        var device = new Builder().build();
        assertNull(device.ipAddress());
    }

    @Test
    public void testIpAddressThroughConstructor() {
        var device = new Builder(ip).build();
        assertEquals(ip, device.ipAddress());
    }

    @Test
    public void testIpAddress() {
        var device = new Builder().ipAddress(ip).build();
        assertEquals(ip, device.ipAddress());
    }

    @Test
    public void testUserAgent() {
        var ua = "Mozila 5";
        var device = new Builder(ip).userAgent(ua).build();
        assertEquals(ua, device.userAgent());
    }

    @Test
    public void testAcceptLanguage() {
        var al = "en-US";
        var device = new Builder(ip).acceptLanguage(al).build();
        assertEquals(al, device.acceptLanguage());
    }

    @Test
    public void testSessionAge() {
        var hour = 3600d;
        var device = new Builder(ip).sessionAge(hour).build();
        assertEquals(hour, device.sessionAge());
    }

    @Test
    public void testSessionId() {
        var id = "foobar";
        var device = new Builder(ip).sessionId(id).build();
        assertEquals(id, device.sessionId());
    }
}
