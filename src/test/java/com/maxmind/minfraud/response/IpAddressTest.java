package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

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

        assertEquals("IP risk", Double.valueOf(99), address.getRisk());
        assertEquals("correct local time", time, address.getLocation().getLocalTime());
        assertEquals("1.2.0.0/16", address.getTraits().getNetwork().toString());
        assertTrue("isAnonymous", address.getTraits().isAnonymous());
        assertTrue("isAnonymousVpn", address.getTraits().isAnonymousVpn());
        assertTrue("isHostingProvider", address.getTraits().isHostingProvider());
        assertTrue("isPublicProxy", address.getTraits().isPublicProxy());
        assertTrue("isTorExitNode", address.getTraits().isTorExitNode());
        assertEquals("mobile country code", "310",
                address.getTraits().getMobileCountryCode());
        assertEquals("mobile network code", "004",
                address.getTraits().getMobileNetworkCode());
        assertEquals("IP risk reason code", "ANONYMOUS_IP",
                address.getRiskReasons().get(0).getCode());
        assertEquals("IP risk reason", "some reason",
                address.getRiskReasons().get(0).getReason());
    }

    @Test
    public void testEmptyObject() throws Exception {
        IpAddress address = this.deserialize(
                IpAddress.class,
                "{}"
        );

        assertNotNull(address.getRiskReasons());
    }
}