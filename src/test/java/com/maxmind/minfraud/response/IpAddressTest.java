package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class IpAddressTest extends AbstractOutputTest {

    @Test
    public void testIpAddress() throws Exception {
        String time = "2015-04-19T12:59:23-01:00";

        IpAddress address = this.deserialize(
            IpAddress.class,
            JSON.std
                .composeString()
                .startObject()
                .put("risk", 99)
                .startArrayField("risk_reasons")
                .startObject()
                .put("code", "ANONYMOUS_IP")
                .put("reason", "some reason")
                .end()
                .end()
                .startObjectField("country")
                .put("is_high_risk", true)
                .end()
                .startObjectField("location")
                .put("local_time", time)
                .end()
                .startObjectField("traits")
                .put("ip_address", "1.2.3.4")
                .put("is_anonymous", true)
                .put("is_anonymous_vpn", true)
                .put("is_hosting_provider", true)
                .put("is_public_proxy", true)
                .put("is_tor_exit_node", true)
                .put("mobile_country_code", "310")
                .put("mobile_network_code", "004")
                .put("network", "1.2.0.0/16")
                .end()
                .end()
                .finish()
        );

        assertEquals(Double.valueOf(99), address.risk(), "IP risk");
        assertEquals(time, address.location().localTime(), "correct local time");
        assertEquals("1.2.0.0/16", address.traits().network().toString());
        assertTrue(address.traits().isAnonymous(), "isAnonymous");
        assertTrue(address.traits().isAnonymousVpn(), "isAnonymousVpn");
        assertTrue(address.traits().isHostingProvider(), "isHostingProvider");
        assertTrue(address.traits().isPublicProxy(), "isPublicProxy");
        assertTrue(address.traits().isTorExitNode(), "isTorExitNode");
        assertEquals(
            "310",
            address.traits().mobileCountryCode(),
            "mobile country code"
        );
        assertEquals(
            "004",
            address.traits().mobileNetworkCode(),
            "mobile network code"
        );
        assertEquals(
            "ANONYMOUS_IP",
            address.riskReasons().get(0).code(),
            "IP risk reason code"
        );
        assertEquals(
            "some reason",
            address.riskReasons().get(0).reason(),
            "IP risk reason"
        );
    }

    @Test
    public void testEmptyObject() throws Exception {
        IpAddress address = this.deserialize(
            IpAddress.class,
            "{}"
        );

        assertNotNull(address.riskReasons());
    }
}