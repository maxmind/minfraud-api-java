package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

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
                .put("brand", "Visa")
                .put("country", "US")
                .put("is_business", true)
                .put("is_issued_in_billing_address_country", true)
                .put("is_prepaid", true)
                .put("is_virtual", true)
                .put("type", "credit")
                .end()
                .finish()
        );

        assertEquals("Bank", cc.issuer().name());
        assertEquals("US", cc.country());
        assertEquals("Visa", cc.brand());
        assertEquals("credit", cc.type());
        assertTrue(cc.isBusiness());
        assertTrue(cc.isPrepaid());
        assertTrue(cc.isVirtual());
        assertTrue(cc.isIssuedInBillingAddressCountry());
    }

}
