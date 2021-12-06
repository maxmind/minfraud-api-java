package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class EmailTest extends AbstractOutputTest {

    @Test
    public void testEmail() throws Exception {
        Email email = this.deserialize(
                Email.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .startObjectField("domain")
                        .put("first_seen", "2014-02-03")
                        .end()
                        .put("is_disposable", false)
                        .put("is_free", false)
                        .put("is_high_risk", true)
                        .put("first_seen", "2017-01-02")
                        .end()
                        .finish()
        );

        assertEquals(LocalDate.parse("2014-02-03"), email.getDomain().getFirstSeen());
        assertFalse(email.isDisposable());
        assertFalse(email.isFree());
        assertTrue(email.isHighRisk());
        assertEquals("2017-01-02", email.getFirstSeen());
        assertEquals(LocalDate.parse("2017-01-02"), email.getFirstSeenDate());
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

        assertNotNull(email.getDomain());
        assertNull(email.isDisposable());
        assertFalse(email.isFree());
        assertTrue(email.isHighRisk());
        assertNull(email.getFirstSeen());
        assertNull(email.getFirstSeenDate());
    }
}
