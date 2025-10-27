package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.maxmind.minfraud.request.Payment.Builder;
import com.maxmind.minfraud.request.Payment.Method;
import com.maxmind.minfraud.request.Payment.Processor;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    public void testMethod() {
        var payment = new Builder().method(Method.CARD).build();
        assertEquals(Method.CARD, payment.method());

        payment = new Builder().method(Method.DIGITAL_WALLET).build();
        assertEquals(Method.DIGITAL_WALLET, payment.method());

        payment = new Builder().method(Method.BUY_NOW_PAY_LATER).build();
        assertEquals(Method.BUY_NOW_PAY_LATER, payment.method());
    }

    @Test
    public void testProcessor() {
        var payment = new Builder().processor(Processor.ADYEN).build();
        assertEquals(Processor.ADYEN, payment.processor());
    }

    @Test
    public void testWasAuthorized() {
        var payment = new Builder().wasAuthorized(true).build();
        assertTrue(payment.wasAuthorized());
    }

    @Test
    public void testDeclineCode() {
        var payment = new Builder().declineCode("declined").build();
        assertEquals("declined", payment.declineCode());
    }
}