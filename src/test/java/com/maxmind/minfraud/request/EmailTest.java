package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Email.Builder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EmailTest {

    @Test
    public void testAddress() throws Exception {
        Email email = new Builder().address("test@test.org").build();
        assertEquals("raw email", "test@test.org", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
        assertEquals("MD5 getter works", "476869598e748d958e819c180af31982", email.getAddressMd5());
    }

    @Test
    public void testMultipleAtAddress() throws Exception {
        Email email = new Builder().address("\"test@test\"@test.org").build();
        assertEquals("raw email", "\"test@test\"@test.org", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
        assertEquals("MD5 getter works", "3dd024e8c10ae51231f5f89a82b01160", email.getAddressMd5());
    }

    @Test
    public void testAddressMd5() throws Exception {
        Email email = new Builder().address("test@test.org").hashAddress().build();
        assertEquals("MD5 generated from email", "476869598e748d958e819c180af31982", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
        assertEquals("MD5 getter works", "476869598e748d958e819c180af31982", email.getAddressMd5());
    }

    @Test
    public void testAddressMd5MultipleTimes() throws Exception {
        Email email = new Builder().address("test@test.org").hashAddress().hashAddress().build();
        assertEquals("MD5 generated from email", "476869598e748d958e819c180af31982", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
        assertEquals("MD5 getter works", "476869598e748d958e819c180af31982", email.getAddressMd5());
    }

    @Test
    public void testHashAddressWithoutAddress() throws Exception {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertEquals("domain is set", "test.org", email.getDomain());
    }

    @Test
    public void testMd5GetsLowercased() throws Exception {
        Email email = new Builder().address("TEST@TEST.org").hashAddress().build();
        assertEquals("MD5 generated from lowercased email", "476869598e748d958e819c180af31982", email.getAddress());
        assertEquals("MD5 generated from lowercased email", "476869598e748d958e819c180af31982", email.getAddressMd5());
    }

    @Test
    public void testGetAddressWithoutSettingIt() throws Exception {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertNull("null address if none set", email.getAddress());
        assertNull("null addressMd5 if none set", email.getAddressMd5());

        Email email2 = new Builder().domain("test.org").hashAddress().build();
        assertNull("null address if none set", email2.getAddress());
        assertNull("null addressMd5 if none set", email2.getAddressMd5());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAddress() throws Exception {
        new Builder().address("a@test@test.org").build();
    }

    @Test
    public void testDomain() throws Exception {
        String domain = "domain.com";
        Email email = new Builder().domain(domain).build();
        assertEquals(domain, email.getDomain());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDomain() throws Exception {
        new Builder().domain(" domain.com").build();
    }
}
