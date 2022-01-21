package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.CreditCard.Builder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class CreditCardTest {

    @Test
    public void testIssuerIdNumber() {
        CreditCard cc = new Builder().issuerIdNumber("123456").build();
        assertEquals("123456", cc.getIssuerIdNumber());

        cc = new Builder().issuerIdNumber("12345678").build();
        assertEquals("12345678", cc.getIssuerIdNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIssuerIdNumberThatIsTooLong() {
        new Builder().issuerIdNumber("1234567").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIssuerIdNumberThatIsTooShort() {
        new Builder().issuerIdNumber("12345").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIssuerIdNumberThatHasLetters() {
        new Builder().issuerIdNumber("12345a").build();
    }

    @Test
    public void testLast4Digits() {
        CreditCard cc = new Builder().last4Digits("1234").build();
        assertEquals("1234", cc.getLast4Digits());
        assertEquals("1234", cc.getLastDigits());
    }

    @Test
    public void testLastDigits() {
        CreditCard cc = new Builder().lastDigits("1234").build();
        assertEquals("1234", cc.getLast4Digits());
        assertEquals("1234", cc.getLastDigits());

        cc = new Builder().lastDigits("12").build();
        assertEquals("12", cc.getLast4Digits());
        assertEquals("12", cc.getLastDigits());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLastDigitsThatIsTooLong() {
        new Builder().lastDigits("12345").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLastDigitsThatIsTooShort() {
        new Builder().lastDigits("123").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLastDigitsThatHasLetters() {
        new Builder().lastDigits("123a").build();
    }

    @Test
    public void testBankName() {
        CreditCard cc = new Builder().bankName("Bank").build();
        assertEquals("Bank", cc.getBankName());
    }

    @Test
    public void testBankPhoneCountryCode() {
        CreditCard cc = new Builder().bankPhoneCountryCode("1").build();
        assertEquals("1", cc.getBankPhoneCountryCode());
    }

    @Test
    public void testBankPhoneNumber() {
        String phone = "231-323-3123";
        CreditCard cc = new Builder().bankPhoneNumber(phone).build();
        assertEquals(phone, cc.getBankPhoneNumber());
    }

    @Test
    public void testAvsResult() {
        CreditCard cc = new Builder().avsResult('Y').build();
        assertEquals(Character.valueOf('Y'), cc.getAvsResult());
    }

    @Test
    public void testCvvResult() {
        CreditCard cc = new Builder().cvvResult('N').build();
        assertEquals(Character.valueOf('N'), cc.getCvvResult());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({"4485921507912924",
            "432312",
            "this is invalid",
            "",
            "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
    })
    public void testInvalidToken(String token) {
        new Builder().token(token).build();
    }

    @Test
    @Parameters({"t4485921507912924",
            "a7f6%gf83fhAu",
            "valid_token"
    })
    public void testValidToken(String token) {
        CreditCard cc = new Builder().token(token).build();
        assertEquals(token, cc.getToken());
    }

    @Test
    public void testWas3dSecureSuccessful() {
        CreditCard cc = new Builder().was3dSecureSuccessful(true).build();
        assertTrue(cc.getWas3dSecureSuccessful());
    }
}