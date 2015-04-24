package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Payment.Builder;
import com.maxmind.minfraud.request.Payment.Processor;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void testProcessor() throws Exception {
        Payment payment = new Builder().processor(Processor.ADYEN).build();
        assertEquals(Processor.ADYEN,payment.getProcessor());
    }

    @Test
    public void testWasAuthorized() throws Exception {
        Payment payment = new Builder().wasAuthorized(true).build();
        assertTrue(payment.wasAuthorized());
    }

    @Test
    public void testDeclineCode() throws Exception {
        Payment payment = new Builder().declineCode("declined").build();
        assertEquals("declined", payment.getDeclineCode());
    }
}