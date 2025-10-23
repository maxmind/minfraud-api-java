package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

public class PhoneTest extends AbstractOutputTest {

    @Test
    public void testPhone() throws Exception {
        Phone phone = this.deserialize(
            Phone.class,
            JSON.std
                .composeString()
                .startObject()
                .put("country", "US")
                .put("is_voip", true)
                .put("matches_postal", false)
                .put("network_operator", "Operator")
                .put("number_type", "fixed")
                .end()
                .finish()
        );

        assertEquals("US", phone.country());
        assertTrue(phone.isVoip());
        assertFalse(phone.matchesPostal());
        assertEquals("Operator", phone.networkOperator());
        assertEquals("fixed", phone.numberType());
    }
}
