package com.maxmind.minfraud.request;

import com.maxmind.minfraud.exception.InvalidInputException;
import com.maxmind.minfraud.request.CreditCard.Builder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditCardTest {

    @Test
    public void testIssuerIdNumber() throws Exception {
        CreditCard cc = new Builder().issuerIdNumber("123456").build();
        assertEquals("123456", cc.getIssuerIdNumber());
    }

    @Test(expected = InvalidInputException.class)
    public void testIssuerIdNumberThatIsTooLong() throws Exception {
        new Builder().issuerIdNumber("1234567").build();
    }

    @Test(expected = InvalidInputException.class)
    public void testIssuerIdNumberThatIsTooShort() throws Exception {
        new Builder().issuerIdNumber("12345").build();
    }

    @Test(expected = InvalidInputException.class)
    public void testIssuerIdNumberThatHasLetters() throws Exception {
        new Builder().issuerIdNumber("12345a").build();
    }

    @Test
    public void testLast4Digits() throws Exception {
        CreditCard cc = new Builder().last4Digits("1234").build();
        assertEquals("1234", cc.getLast4Digits());
    }

    @Test(expected = InvalidInputException.class)
    public void testLast4DigitsThatIsTooLong() throws Exception {
        new Builder().last4Digits("12345").build();
    }

    @Test(expected = InvalidInputException.class)
    public void testLast4DigitsThatIsTooShort() throws Exception {
        new Builder().last4Digits("123").build();
    }

    @Test(expected = InvalidInputException.class)
    public void testLast4DigitsThatHasLetters() throws Exception {
        new Builder().last4Digits("123a").build();
    }

    @Test
    public void testBankName() throws Exception {
        CreditCard cc = new Builder().bankName("Bank").build();
        assertEquals("Bank", cc.getBankName());
    }

    @Test
    public void testBankPhoneCountryCode() throws Exception {
        CreditCard cc = new Builder().bankPhoneCountryCode("1").build();
        assertEquals("1", cc.getBankPhoneCountryCode());
    }

    @Test
    public void testBankPhoneNumber() throws Exception {
        String phone = "231-323-3123";
        CreditCard cc = new Builder().bankPhoneNumber(phone).build();
        assertEquals(phone, cc.getBankPhoneNumber());
    }

    @Test
    public void testAvsResult() throws Exception {
        CreditCard cc = new Builder().avsResult('Y').build();
        assertEquals(Character.valueOf('Y'), cc.getAvsResult());
    }

    @Test
    public void testCvvResult() throws Exception {
        CreditCard cc = new Builder().cvvResult('N').build();
        assertEquals(Character.valueOf('N'), cc.getCvvResult());
    }
}