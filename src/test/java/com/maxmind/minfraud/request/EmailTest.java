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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

    @Test
    public void testAddress() {
        Email email = new Builder().address("test@test.org").build();
        assertEquals("test@test.org", email.address(), "raw email");
        assertEquals("test.org", email.domain(), "domain set from email");
    }

    @Test
    public void testMultipleAtAddress() {
        Email email = new Builder().address("\"test@test\"@test.org").build();
        assertEquals("\"test@test\"@test.org", email.address(), "raw email");
        assertEquals("test.org", email.domain(), "domain set from email");
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
            assertEquals(address, email.address(), "raw email");
            assertEquals(addresses.get(address), email.domain(), "domain set from email");
        }
    }

    @Test
    public void testAddressMd5() {
        Email email = new Builder().address("test@test.org").hashAddress().build();
        assertEquals(
            "476869598e748d958e819c180af31982",
            email.address(),
            "MD5 generated from email"
        );
        assertEquals("test.org", email.domain(), "domain set from email");
    }

    @Test
    public void testAddressMd5MultipleTimes() {
        Email email = new Builder().address("test@test.org").hashAddress().hashAddress().build();
        assertEquals(
            "476869598e748d958e819c180af31982",
            email.address(),
            "MD5 generated from email"
        );
        assertEquals("test.org", email.domain(), "domain set from email");
    }

    @Test
    public void testHashAddressWithoutAddress() {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertEquals("test.org", email.domain(), "domain is set");
    }

    @Test
    public void testMd5GetsLowercased() {
        Email email = new Builder().address("TEST@TEST.org").hashAddress().build();
        assertEquals(
            "476869598e748d958e819c180af31982",
            email.address(),
            "MD5 generated from lowercased email"
        );
    }

    @Test
    public void testGetAddressWithoutSettingIt() {
        Email email = new Builder().domain("test.org").hashAddress().build();
        assertNull(email.address(), "null address if none set");

        Email email2 = new Builder().domain("test.org").hashAddress().build();
        assertNull(email2.address(), "null address if none set");
    }

    @Test
    public void testNormalizing() throws NoSuchAlgorithmException {
        Email e;

        e = new Builder().address("test@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals("maxmind.com", e.domain(), "domain");

        e = new Builder().address("Test@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals("maxmind.com", e.domain(), "domain");

        e = new Builder(false).address("  Test@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals("maxmind.com", e.domain(), "domain");

        e = new Builder().address("Test+alias@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals("maxmind.com", e.domain(), "domain");

        e = new Builder().address("Test+007+008@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals("maxmind.com", e.domain(), "domain");

        e = new Builder().address("Test+@maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals("maxmind.com", e.domain(), "domain");

        e = new Builder(false).address("Test@maxmind.com.").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals("maxmind.com.", e.domain(), "domain");

        e = new Builder().address("+@maxmind.com").hashAddress().build();
        assertEquals("aa57884e48f0dda9fc6f4cb2bffb1dd2", e.address(), "MD5");
        assertEquals("maxmind.com", e.domain(), "domain");

        e = new Builder(false).address("Test@ maxmind.com").hashAddress().build();
        assertEquals("977577b140bfb7c516e4746204fbdb01", e.address(), "MD5");
        assertEquals(" maxmind.com", e.domain(), "domain");

        e = new Builder().address("Test+foo@yahoo.com").hashAddress().build();
        assertEquals("a5f830c699fd71ad653aa59fa688c6d9", e.address(), "MD5");
        assertEquals("yahoo.com", e.domain(), "domain");

        e = new Builder().address("Test-foo@yahoo.com").hashAddress().build();
        assertEquals("88e478531ab3bc303f1b5da82c2e9bbb", e.address(), "MD5");
        assertEquals("yahoo.com", e.domain(), "domain");

        e = new Builder().address("Test-foo-foo2@yahoo.com").hashAddress().build();
        assertEquals("88e478531ab3bc303f1b5da82c2e9bbb", e.address(), "MD5");
        assertEquals("yahoo.com", e.domain(), "domain");

        e = new Builder().address("Test-foo@gmail.com").hashAddress().build();
        assertEquals("6f3ff986fa5e830dbbf08a942777a17c", e.address(), "MD5");
        assertEquals("gmail.com", e.domain(), "domain");

        e = new Builder().address("test@gmail.com").hashAddress().build();
        assertEquals("1aedb8d9dc4751e229a335e371db8058", e.address(), "MD5");
        assertEquals("gmail.com", e.domain(), "domain");

        e = new Builder().address("test@gamil.com").hashAddress().build();
        assertEquals("1aedb8d9dc4751e229a335e371db8058", e.address(), "MD5");
        assertEquals("gamil.com", e.domain(), "domain");

        e = new Builder().address("test@bücher.com").hashAddress().build();
        assertEquals("24948acabac551360cd510d5e5e2b464", e.address(), "MD5");
        assertEquals("bücher.com", e.domain(), "domain");

        e = new Builder().address("Test+alias@Bücher.com").hashAddress().build();
        assertEquals("24948acabac551360cd510d5e5e2b464", e.address(), "MD5");
        assertEquals("Bücher.com", e.domain(), "domain");

        e = new Builder(false).address("test").hashAddress().build();
        assertEquals("098f6bcd4621d373cade4e832627b4f6", e.address(), "MD5");
        assertNull(e.domain(), "domain");

        e = new Builder(false).address("test@").hashAddress().build();
        assertEquals("246a848af2f8394e3adbc738dbe43720", e.address(), "MD5");
        assertNull(e.domain(), "domain");

        e = new Builder(false).address("test@.").hashAddress().build();
        assertEquals("246a848af2f8394e3adbc738dbe43720", e.address(), "MD5");
        assertEquals(".", e.domain(), "domain");

        e = new Builder(false).address("foo@googlemail.com").hashAddress().build();
        assertEquals(toMD5("foo@gmail.com"), e.address(), "MD5");
        assertEquals("googlemail.com", e.domain(), "domain");

        e = new Builder(false).address("foo.bar@gmail.com").hashAddress().build();
        assertEquals(toMD5("foobar@gmail.com"), e.address(), "MD5");
        assertEquals("gmail.com", e.domain(), "domain");

        e = new Builder(false).address("alias@user.fastmail.com").hashAddress().build();
        assertEquals(toMD5("user@fastmail.com"), e.address(), "MD5");
        assertEquals("user.fastmail.com", e.domain(), "domain");

        e = new Builder(false).address("foo@bar.example.com").hashAddress().build();
        assertEquals(toMD5("foo@bar.example.com"), e.address(), "MD5");
        assertEquals("bar.example.com", e.domain(), "domain");

        e = new Builder(false).address("foo-bar@ymail.com").hashAddress().build();
        assertEquals(toMD5("foo@ymail.com"), e.address(), "MD5");
        assertEquals("ymail.com", e.domain(), "domain");

        e = new Builder(false).address("foo@example.com.com").hashAddress().build();
        assertEquals(toMD5("foo@example.com"), e.address(), "MD5");
        assertEquals("example.com.com", e.domain(), "domain");

        e = new Builder(false).address("foo@example.comfoo").hashAddress().build();
        assertEquals(toMD5("foo@example.comfoo"), e.address(), "MD5");
        assertEquals("example.comfoo", e.domain(), "domain");

        e = new Builder(false).address("foo@example.cam").hashAddress().build();
        assertEquals(toMD5("foo@example.cam"), e.address(), "MD5");
        assertEquals("example.cam", e.domain(), "domain");

        e = new Builder(false).address("foo@10000gmail.com").hashAddress().build();
        assertEquals(toMD5("foo@gmail.com"), e.address(), "MD5");
        assertEquals("10000gmail.com", e.domain(), "domain");

        e = new Builder(false).address("foo@example.comcom").hashAddress().build();
        assertEquals(toMD5("foo@example.com"), e.address(), "MD5");
        assertEquals("example.comcom", e.domain(), "domain");

        e = new Builder(false).address("foo@example.com.").hashAddress().build();
        assertEquals(toMD5("foo@example.com"), e.address(), "MD5");
        assertEquals("example.com.", e.domain(), "domain");

        e = new Builder(false).address("foo@example.com...").hashAddress().build();
        assertEquals(toMD5("foo@example.com"), e.address(), "MD5");
        assertEquals("example.com...", e.domain(), "domain");

        e = new Builder().address("example@bu\u0308cher.com").hashAddress().build();
        assertEquals("2b21bc76dab3c8b1622837c1d698936c", e.address(), "MD5");
        e = new Builder().address("example@b\u00FCcher.com").hashAddress().build();
        assertEquals("2b21bc76dab3c8b1622837c1d698936c", e.address(), "MD5");

        e = new Builder().address("bu\u0308cher@example.com").hashAddress().build();
        assertEquals("53550c712b146287a2d0dd30e5ed6f4b", e.address(), "MD5");
        e = new Builder().address("b\u00FCcher@example.com").hashAddress().build();
        assertEquals("53550c712b146287a2d0dd30e5ed6f4b", e.address(), "MD5");
    }

    private String toMD5(String s) throws NoSuchAlgorithmException {
        MessageDigest d = MessageDigest.getInstance("MD5");
        d.update(s.getBytes(StandardCharsets.UTF_8));
        BigInteger i = new BigInteger(1, d.digest());
        return String.format("%032x", i);
    }

    @ParameterizedTest(name = "Run #{index}: email = \"{0}\"")
    @ValueSource(strings = {
            "test.com",
            "test@",
            "@test.com",
            "",
            "  ",
            "test@test com.com",
            "test@test_domain.com",
            "test@-test.com",
            "test@test-.com",
            "test@.test.com",
            "test@test..com",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@test.com",
            "test@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.com"
    })
    void testInvalidAddresses(String invalidAddress) {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().address(invalidAddress).build()
        );
    }

    @Test
    public void testDomain() {
        String domain = "domain.com";
        Email email = new Builder().domain(domain).build();
        assertEquals(domain, email.domain());
    }

    @Test
    public void testDomainWithoutValidation() {
        String domain = "bad domain @!";
        Email email = new Builder(false).domain(domain).build();
        assertEquals(domain, email.domain());
    }

    @ParameterizedTest(name = "Run #{index}: domain = \"{0}\"")
    @ValueSource(strings = {
            "example",
            "",
            "   ",
            " domain.com",
            "domain.com ",
            "domain com.com",
            "domain_name.com",
            "domain$.com",
            "-domain.com",
            "domain-.com",
            "domain..com",
            ".domain.com",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.com",
            "a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a" +
            ".a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a" +
            ".a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a" +
            ".a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a" +
            ".com",
            "xn--.com"
    })
    void testInvalidDomains(String invalidDomain) {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().domain(invalidDomain).build()
        );
    }
}
