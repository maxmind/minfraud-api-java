package com.maxmind.minfraud.input;

import com.maxmind.minfraud.exception.InvalidInputException;
import com.maxmind.minfraud.input.Email.Builder;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {

    @Test
    public void testAddress() throws Exception {
        Email email = new Builder().address("test@test.org").build();
        assertEquals("MD5 generated from email", "476869598e748d958e819c180af31982", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
    }

    @Test ( expected = InvalidInputException.class )
    public void testInvalidAddress() throws Exception {
       new Builder().address("a@test@test.org").build();
    }

    @Test
    public void testDomain() throws Exception {
        String domain = "domain.com";
        Email email = new Builder().domain(domain).build();
        assertEquals(domain, email.getDomain());
    }

    @Test ( expected = InvalidInputException.class )
    public void testInvalidDomain() throws Exception {
        new Builder().domain( " domain.com").build();
    }
}