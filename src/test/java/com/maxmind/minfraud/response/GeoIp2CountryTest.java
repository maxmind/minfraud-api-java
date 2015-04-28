package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeoIp2CountryTest extends AbstractOutputTest {


    @Test
    public void testIsHighRisk() throws Exception {
        GeoIp2Country country = this.deserialize(
                GeoIp2Country.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("is_high_risk", true)
                        .end()
                        .finish()
        );

        assertTrue(country.isHighRisk());
    }
}