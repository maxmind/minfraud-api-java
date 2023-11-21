package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import com.maxmind.geoip2.model.ConnectionTypeResponse.ConnectionType;
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
                        .put("connection_type", "Satellite")
                        .put("ip_address", "1.2.3.4")
                        .put("is_anonymous", true)
                        .put("is_anonymous_vpn", true)
                        .put("is_hosting_provider", true)
                        .put("is_public_proxy", true)
                        .put("is_tor_exit_node", true)
                        .put("network", "1.2.0.0/16")
                        .end()
                        .end()
                        .finish()
        );

        assertEquals("IP risk", new Double(99), address.getRisk());
        assertEquals("correct local time", time, address.getLocation().getLocalTime());
        assertEquals("1.2.0.0/16", address.getTraits().getNetwork().toString());
        assertTrue("isHighRisk", address.getCountry().isHighRisk());
        assertEquals("getConnectionType", ConnectionType.SATELLITE, address.getTraits().getConnectionType());
        assertTrue("isAnonymous", address.getTraits().isAnonymous());
        assertTrue("isAnonymousVpn", address.getTraits().isAnonymousVpn());
        assertTrue("isHostingProvider", address.getTraits().isHostingProvider());
        assertTrue("isPublicProxy", address.getTraits().isPublicProxy());
        assertTrue("isTorExitNode", address.getTraits().isTorExitNode());
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
