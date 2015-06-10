package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IssuerTest extends AbstractOutputTest {

    @Test
    public void testIssuer() throws Exception {
        String phone = "132-342-2131";

        Issuer issuer = this.deserialize(
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

        assertEquals("bank name", "Bank", issuer.getName());
        assertTrue("provided name matches", issuer.matchesProvidedName());
        assertEquals("phone", phone, issuer.getPhoneNumber());
        assertTrue("provided phone matches", issuer.matchesProvidedPhoneNumber());
    }
}