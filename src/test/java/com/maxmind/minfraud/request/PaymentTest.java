package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.maxmind.minfraud.request.Payment.Builder;
import com.maxmind.minfraud.request.Payment.Processor;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    public void testProcessor() {
        Payment payment = new Builder().processor(Processor.ADYEN).build();
        assertEquals(Processor.ADYEN, payment.getProcessor());
    }

    @Test
    public void testWasAuthorized() {
        Payment payment = new Builder().wasAuthorized(true).build();
        assertTrue(payment.wasAuthorized());
    }

    @Test
    public void testDeclineCode() {
        Payment payment = new Builder().declineCode("declined").build();
        assertEquals("declined", payment.getDeclineCode());
    }
}