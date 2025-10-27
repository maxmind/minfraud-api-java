package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

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

        assertEquals("Bank", issuer.name(), "bank name");
        assertTrue(issuer.matchesProvidedName(), "provided name matches");
        assertEquals(phone, issuer.phoneNumber(), "phone");
        assertTrue(issuer.matchesProvidedPhoneNumber(), "provided phone matches");
    }
}