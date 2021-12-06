package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Email.Builder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EmailTest {

    @Test
    public void testAddress() {
        Email email = new Builder().address("test@test.org").build();
        assertEquals("raw email", "test@test.org", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
    }

    @Test
    public void testMultipleAtAddress() {
        Email email = new Builder().address("\"test@test\"@test.org").build();
        assertEquals("raw email", "\"test@test\"@test.org", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
    }

    @Test
    public void testAddressWithNoValidation() {
        Map<String, String> addresses = new HashMap<>() {{
            put("test", null);
            put("@test", "test");
            put("test@", null);
            put("test@test.generic", "test.generic");
        }};

        for (String address : addresses.keySet()) {
            Email email = new Builder(false).address(address).build();
            assertEquals("raw email", address, email.getAddress());
            assertEquals("domain set from email", addresses.get(address), email.getDomain());
        }
    }

    @Test
    public void testAddressMd5() {
        Email email = new Builder().address("test@test.org").hashAddress().build();
        assertEquals("MD5 generated from email", "476869598e748d958e819c180af31982", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
    }

    @Test
    public void testAddressMd5MultipleTimes() {
        Email email = new Builder().address("test@test.org").hashAddress().hashAddress().build();
        assertEquals("MD5 generated from email", "476869598e748d958e819c180af31982", email.getAddress());
        assertEquals("domain set from email", "test.org", email.getDomain());
    }

    @Test
    public void testHashAddressWithoutAddress() {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertEquals("domain is set", "test.org", email.getDomain());
    }

    @Test
    public void testMd5GetsLowercased() {
        Email email = new Builder().address("TEST@TEST.org").hashAddress().build();
        assertEquals("MD5 generated from lowercased email", "476869598e748d958e819c180af31982", email.getAddress());
    }

    @Test
    public void testGetAddressWithoutSettingIt() {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertNull("null address if none set", email.getAddress());

        Email email2 = new Builder().domain("test.org").hashAddress().build();
        assertNull("null address if none set", email2.getAddress());
    }

    @Test
    public void testNormalizing() {
        Email e;

        e = new Builder().address("test@maxmind.com").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", "maxmind.com", e.getDomain());

        e = new Builder().address("Test@maxmind.com").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", "maxmind.com", e.getDomain());

        e = new Builder(false).address("  Test@maxmind.com").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", "maxmind.com", e.getDomain());

        e = new Builder().address("Test+alias@maxmind.com").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", "maxmind.com", e.getDomain());

        e = new Builder().address("Test+007+008@maxmind.com").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", "maxmind.com", e.getDomain());

        e = new Builder().address("Test+@maxmind.com").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", "maxmind.com", e.getDomain());

        e = new Builder(false).address("Test@maxmind.com.").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", "maxmind.com.", e.getDomain());

        e = new Builder().address("+@maxmind.com").hashAddress().build();
        assertEquals("MD5", "aa57884e48f0dda9fc6f4cb2bffb1dd2", e.getAddress());
        assertEquals("domain", "maxmind.com", e.getDomain());

        e = new Builder(false).address("Test@ maxmind.com").hashAddress().build();
        assertEquals("MD5", "977577b140bfb7c516e4746204fbdb01", e.getAddress());
        assertEquals("domain", " maxmind.com", e.getDomain());

        e = new Builder().address("Test+foo@yahoo.com").hashAddress().build();
        assertEquals("MD5", "a5f830c699fd71ad653aa59fa688c6d9", e.getAddress());
        assertEquals("domain", "yahoo.com", e.getDomain());

        e = new Builder().address("Test-foo@yahoo.com").hashAddress().build();
        assertEquals("MD5", "88e478531ab3bc303f1b5da82c2e9bbb", e.getAddress());
        assertEquals("domain", "yahoo.com", e.getDomain());

        e = new Builder().address("Test-foo-foo2@yahoo.com").hashAddress().build();
        assertEquals("MD5", "88e478531ab3bc303f1b5da82c2e9bbb", e.getAddress());
        assertEquals("domain", "yahoo.com", e.getDomain());

        e = new Builder().address("Test-foo@gmail.com").hashAddress().build();
        assertEquals("MD5", "6f3ff986fa5e830dbbf08a942777a17c", e.getAddress());
        assertEquals("domain", "gmail.com", e.getDomain());

        e = new Builder().address("test@gmail.com").hashAddress().build();
        assertEquals("MD5", "1aedb8d9dc4751e229a335e371db8058", e.getAddress());
        assertEquals("domain", "gmail.com", e.getDomain());

        e = new Builder().address("test@gamil.com").hashAddress().build();
        assertEquals("MD5", "1aedb8d9dc4751e229a335e371db8058", e.getAddress());
        assertEquals("domain", "gamil.com", e.getDomain());

        e = new Builder().address("test@bücher.com").hashAddress().build();
        assertEquals("MD5", "24948acabac551360cd510d5e5e2b464", e.getAddress());
        assertEquals("domain", "bücher.com", e.getDomain());

        e = new Builder().address("Test+alias@Bücher.com").hashAddress().build();
        assertEquals("MD5", "24948acabac551360cd510d5e5e2b464", e.getAddress());
        assertEquals("domain", "Bücher.com", e.getDomain());

        e = new Builder(false).address("test").hashAddress().build();
        assertEquals("MD5", "098f6bcd4621d373cade4e832627b4f6", e.getAddress());
        assertNull("domain", e.getDomain());

        e = new Builder(false).address("test@").hashAddress().build();
        assertEquals("MD5", "246a848af2f8394e3adbc738dbe43720", e.getAddress());
        assertNull("domain", e.getDomain());

        e = new Builder(false).address("test@.").hashAddress().build();
        assertEquals("MD5", "246a848af2f8394e3adbc738dbe43720", e.getAddress());
        assertEquals("domain", ".", e.getDomain());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAddress() {
        new Builder().address("a@test@test.org").build();
    }

    @Test
    public void testDomain() {
        String domain = "domain.com";
        Email email = new Builder().domain(domain).build();
        assertEquals(domain, email.getDomain());
    }

    @Test
    public void testDomainWithoutValidation() {
        String domain = "bad domain @!";
        Email email = new Builder(false).domain(domain).build();
        assertEquals(domain, email.getDomain());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDomain() {
        new Builder().domain(" domain.com").build();
    }
}
