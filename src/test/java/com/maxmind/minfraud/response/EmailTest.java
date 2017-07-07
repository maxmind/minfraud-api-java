package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest extends AbstractOutputTest {

    @Test
    public void testEmail() throws Exception {
        Email email = this.deserialize(
                Email.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("is_free", false)
                        .put("is_high_risk", true)
                        .put("first_seen", "2017-01-02")
                        .end()
                        .finish()
        );

        assertFalse(email.isFree());
        assertTrue(email.isHighRisk());
        assertEquals(email.getFirstSeen(), "2017-01-02");
    }

    @Test
    public void testEmailWithoutFirstSeen() throws Exception {
        Email email = this.deserialize(
                Email.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("is_free", false)
                        .put("is_high_risk", true)
                        .end()
                        .finish()
        );

        assertFalse(email.isFree());
        assertTrue(email.isHighRisk());
        assertNull(email.getFirstSeen());
    }
}
