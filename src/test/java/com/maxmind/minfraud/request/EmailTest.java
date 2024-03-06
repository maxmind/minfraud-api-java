package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.maxmind.minfraud.request.Email.Builder;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void testAddress() {
        Email email = new Builder().address("test@test.org").build();
        assertEquals("test@test.org", email.getAddress(), "raw email");
        assertEquals("test.org", email.getDomain(), "domain set from email");
    }

    @Test
    public void testMultipleAtAddress() {
        Email email = new Builder().address("\"test@test\"@test.org").build();
        assertEquals("\"test@test\"@test.org", email.getAddress(), "raw email");
        assertEquals("test.org", email.getDomain(), "domain set from email");
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
            assertEquals(address, email.getAddress(), "raw email");
            assertEquals(addresses.get(address), email.getDomain(), "domain set from email");
        }
    }

    @Test
    public void testAddressMd5() {
        Email email = new Builder().address("test@test.org").hashAddress().build();
        assertEquals(
            "476869598e748d958e819c180af31982",
            email.getAddress(),
            "MD5 generated from email"
        );
        assertEquals("test.org", email.getDomain(), "domain set from email");
    }

    @Test
    public void testAddressMd5MultipleTimes() {
        Email email = new Builder().address("test@test.org").hashAddress().hashAddress().build();
        assertEquals(
            "476869598e748d958e819c180af31982",
            email.getAddress(),
            "MD5 generated from email"
        );
        assertEquals("test.org", email.getDomain(), "domain set from email");
    }

    @Test
    public void testHashAddressWithoutAddress() {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertEquals("test.org", email.getDomain(), "domain is set");
    }

    @Test
    public void testMd5GetsLowercased() {
        Email email = new Builder().address("TEST@TEST.org").hashAddress().build();
        assertEquals(
            "476869598e748d958e819c180af31982",
            email.getAddress(),
            "MD5 generated from lowercased email"
        );
    }

    @Test
    public void testGetAddressWithoutSettingIt() {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertNull(email.getAddress(), "null address if none set");

        Email email2 = new Builder().domain("test.org").hashAddress().build();
        assertNull(email2.getAddress(), "null address if none set");
    }

    @Test
    public void testNormalizing() throws NoSuchAlgorithmException {
        Email e;

        e = new Builder().address("test@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals("maxmind.com", e.getDomain(), "domain");

        e = new Builder().address("Test@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals("maxmind.com", e.getDomain(), "domain");

        e = new Builder(false).address("  Test@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals("maxmind.com", e.getDomain(), "domain");

        e = new Builder().address("Test+alias@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals("maxmind.com", e.getDomain(), "domain");

        e = new Builder().address("Test+007+008@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals("maxmind.com", e.getDomain(), "domain");

        e = new Builder().address("Test+@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals("maxmind.com", e.getDomain(), "domain");

        e = new Builder(false).address("Test@maxmind.com.").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals("maxmind.com.", e.getDomain(), "domain");

        e = new Builder().address("+@maxmind.com").hashAddress().build();
        assertEquals("aa57884e48f0dda9fc6f4cb2bffb1dd2", e.getAddress(), "MD5");
        assertEquals("maxmind.com", e.getDomain(), "domain");

        e = new Builder(false).address("Test@ maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.getAddress(), "MD5");
        assertEquals(" maxmind.com", e.getDomain(), "domain");

        e = new Builder().address("Test+foo@yahoo.com").hashAddress().build();
        assertEquals("a5f830c699fd71ad653aa59fa688c6d9", e.getAddress(), "MD5");
        assertEquals("yahoo.com", e.getDomain(), "domain");

        e = new Builder().address("Test-foo@yahoo.com").hashAddress().build();
        assertEquals("88e478531ab3bc303f1b5da82c2e9bbb", e.getAddress(), "MD5");
        assertEquals("yahoo.com", e.getDomain(), "domain");

        e = new Builder().address("Test-foo-foo2@yahoo.com").hashAddress().build();
        assertEquals("88e478531ab3bc303f1b5da82c2e9bbb", e.getAddress(), "MD5");
        assertEquals("yahoo.com", e.getDomain(), "domain");

        e = new Builder().address("Test-foo@gmail.com").hashAddress().build();
        assertEquals("6f3ff986fa5e830dbbf08a942777a17c", e.getAddress(), "MD5");
        assertEquals("gmail.com", e.getDomain(), "domain");

        e = new Builder().address("test@gmail.com").hashAddress().build();
        assertEquals("1aedb8d9dc4751e229a335e371db8058", e.getAddress(), "MD5");
        assertEquals("gmail.com", e.getDomain(), "domain");

        e = new Builder().address("test@gamil.com").hashAddress().build();
        assertEquals("1aedb8d9dc4751e229a335e371db8058", e.getAddress(), "MD5");
        assertEquals("gamil.com", e.getDomain(), "domain");

        e = new Builder().address("test@bücher.com").hashAddress().build();
        assertEquals("24948acabac551360cd510d5e5e2b464", e.getAddress(), "MD5");
        assertEquals("bücher.com", e.getDomain(), "domain");

        e = new Builder().address("Test+alias@Bücher.com").hashAddress().build();
        assertEquals("24948acabac551360cd510d5e5e2b464", e.getAddress(), "MD5");
        assertEquals("Bücher.com", e.getDomain(), "domain");

        e = new Builder(false).address("test").hashAddress().build();
        assertEquals("098f6bcd4621d373cade4e832627b4f6", e.getAddress(), "MD5");
        assertNull(e.getDomain(), "domain");

        e = new Builder(false).address("test@").hashAddress().build();
        assertEquals("246a848af2f8394e3adbc738dbe43720", e.getAddress(), "MD5");
        assertNull(e.getDomain(), "domain");

        e = new Builder(false).address("test@.").hashAddress().build();
        assertEquals("246a848af2f8394e3adbc738dbe43720", e.getAddress(), "MD5");
        assertEquals(".", e.getDomain(), "domain");

        e = new Builder(false).address("foo@googlemail.com").hashAddress().build();
        assertEquals(toMD5("foo@gmail.com"), e.getAddress(), "MD5");
        assertEquals("googlemail.com", e.getDomain(), "domain");

        e = new Builder(false).address("foo.bar@gmail.com").hashAddress().build();
        assertEquals(toMD5("foobar@gmail.com"), e.getAddress(), "MD5");
        assertEquals("gmail.com", e.getDomain(), "domain");

        e = new Builder(false).address("alias@user.fastmail.com").hashAddress().build();
        assertEquals(toMD5("user@fastmail.com"), e.getAddress(), "MD5");
        assertEquals("user.fastmail.com", e.getDomain(), "domain");

        e = new Builder(false).address("foo@bar.example.com").hashAddress().build();
        assertEquals(toMD5("foo@bar.example.com"), e.getAddress(), "MD5");
        assertEquals("bar.example.com", e.getDomain(), "domain");

        e = new Builder(false).address("foo-bar@ymail.com").hashAddress().build();
        assertEquals(toMD5("foo@ymail.com"), e.getAddress(), "MD5");
        assertEquals("ymail.com", e.getDomain(), "domain");

        e = new Builder(false).address("foo@example.com.com").hashAddress().build();
        assertEquals(toMD5("foo@example.com"), e.getAddress(), "MD5");
        assertEquals("example.com.com", e.getDomain(), "domain");

        e = new Builder(false).address("foo@example.comfoo").hashAddress().build();
        assertEquals(toMD5("foo@example.com"), e.getAddress(), "MD5");
        assertEquals("example.comfoo", e.getDomain(), "domain");

        e = new Builder(false).address("foo@example.cam").hashAddress().build();
        assertEquals(toMD5("foo@example.com"), e.getAddress(), "MD5");
        assertEquals("example.cam", e.getDomain(), "domain");

        e = new Builder(false).address("foo@10000gmail.com").hashAddress().build();
        assertEquals(toMD5("foo@gmail.com"), e.getAddress(), "MD5");
        assertEquals("10000gmail.com", e.getDomain(), "domain");
    }

    private String toMD5(String s) throws NoSuchAlgorithmException {
        MessageDigest d = MessageDigest.getInstance("MD5");
        d.update(s.getBytes(StandardCharsets.UTF_8));
        BigInteger i = new BigInteger(1, d.digest());
        return String.format("%032x", i);
    }

    @Test
    public void testInvalidAddress() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().address("a@test@test.org").build()
        );
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

    @Test
    public void testInvalidDomain() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().domain(" domain.com").build()
        );
    }
}
