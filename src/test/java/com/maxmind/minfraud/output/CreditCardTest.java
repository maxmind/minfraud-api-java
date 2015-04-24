package com.maxmind.minfraud.output;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCardTest extends AbstractOutputTest {

    @Test
    public void testCreditCard() throws Exception {
        CreditCard cc = this.deserialize(
                CreditCard.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .startObjectField("issuer")
                        .put("name", "Bank")
                        .end()
                        .put("country", "US")
                        .put("is_issued_in_billing_address_country", true)
                        .put("is_prepaid", true)
                        .end()
                        .finish()
        );

        assertEquals("Bank", cc.getIssuer().getName());
        assertEquals("US", cc.getCountry());
        assertTrue(cc.isPrepaid());
        assertTrue(cc.isIssuedInBillingAddressCountry());
    }
}