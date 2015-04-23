package com.maxmind.minfraud.input;

import com.maxmind.minfraud.exception.InvalidInputException;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {

    @Test
    public void testAddress() throws Exception {
        Email email = new Email.Builder().address("test@test.org").build();
        assertEquals("476869598e748d958e819c180af31982", email.getAddress());
        assertEquals("test.org", email.getDomain());
    }

    @Test ( expected = InvalidInputException.class )
    public void testInvalidAddress() throws Exception {
       new Email.Builder().address("a@test@test.org").build();
    }

    @Test
    public void testDomain() throws Exception {
        String domain = "domain.com";
        Email email = new Email.Builder().domain(domain).build();
        assertEquals(domain, email.getDomain());
    }

    @Test ( expected = InvalidInputException.class )
    public void testInvalidDomain() throws Exception {
        new Email.Builder().domain( " domain.com").build();
    }
}