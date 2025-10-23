package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.maxmind.minfraud.request.CreditCard.Builder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CreditCardTest {

    @Test
    public void testIssuerIdNumber() {
        CreditCard cc = new Builder().issuerIdNumber("123456").build();
        assertEquals("123456", cc.issuerIdNumber());

        cc = new Builder().issuerIdNumber("12345678").build();
        assertEquals("12345678", cc.issuerIdNumber());
    }

    @Test
    public void testIssuerIdNumberThatIsTooLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().issuerIdNumber("1234567").build()
        );
    }

    @Test
    public void testIssuerIdNumberThatIsTooShort() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().issuerIdNumber("12345").build()
        );
    }

    @Test
    public void testIssuerIdNumberThatHasLetters() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().issuerIdNumber("12345a").build()
        );
    }

    @Test
    public void testLastDigits() {
        CreditCard cc = new Builder().lastDigits("1234").build();
        assertEquals("1234", cc.lastDigits());

        cc = new Builder().lastDigits("12").build();
        assertEquals("12", cc.lastDigits());
    }

    @Test
    public void testLastDigitsThatIsTooLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().lastDigits("12345").build()
        );
    }

    @Test
    public void testLastDigitsThatIsTooShort() {

        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().lastDigits("123").build()
        );
    }

    @Test
    public void testLastDigitsThatHasLetters() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().lastDigits("123a").build()
        );
    }

    @Test
    public void testBankName() {
        CreditCard cc = new Builder().bankName("Bank").build();
        assertEquals("Bank", cc.bankName());
    }

    @Test
    public void testBankPhoneCountryCode() {
        CreditCard cc = new Builder().bankPhoneCountryCode("1").build();
        assertEquals("1", cc.bankPhoneCountryCode());
    }

    @Test
    public void testBankPhoneNumber() {
        String phone = "231-323-3123";
        CreditCard cc = new Builder().bankPhoneNumber(phone).build();
        assertEquals(phone, cc.bankPhoneNumber());
    }

    @Test
    public void testCountry() {
        String country = "CA";
        CreditCard cc = new Builder().country(country).build();
        assertEquals(country, cc.country());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ca", "USA", "C1"})
    public void testInvalidCountry(String country) {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().country(country).build()
        );
    }

    @Test
    public void testAvsResult() {
        CreditCard cc = new Builder().avsResult('Y').build();
        assertEquals(Character.valueOf('Y'), cc.avsResult());
    }

    @Test
    public void testCvvResult() {
        CreditCard cc = new Builder().cvvResult('N').build();
        assertEquals(Character.valueOf('N'), cc.cvvResult());
    }

    @ParameterizedTest
    @ValueSource(strings = {"4485921507912924",
        "432312",
        "this is invalid",
        "",
        "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
    })
    public void testInvalidToken(String token) {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().token(token).build()
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"t4485921507912924",
        "a7f6%gf83fhAu",
        "valid_token"
    })
    public void testValidToken(String token) {
        CreditCard cc = new Builder().token(token).build();
        assertEquals(token, cc.token());
    }

    @Test
    public void testWas3dSecureSuccessful() {
        CreditCard cc = new Builder().was3dSecureSuccessful(true).build();
        assertTrue(cc.was3dSecureSuccessful());
    }
}
