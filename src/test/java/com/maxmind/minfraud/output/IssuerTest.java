package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

public class IssuerTest extends AbstractOutputTest {

    @Test
    public void testIpLocation() throws Exception {
        String phone = "132-342-2131";

           Issuer issuer = deserialize(
                Issuer.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("name", "Bank")
                        .put("matches_provided_name", true)
                        .put("phone_number", phone)
                        .put("matches_provided_phone_number", true)
                        .end()
                        .finish()
        );

        assertEquals("Bank", issuer.getName());
        assertTrue(issuer.matchesProvidedName());
        assertEquals(phone, issuer.getPhoneNumber());
        assertTrue(issuer.matchesProvidedPhoneNumber());
    }
}