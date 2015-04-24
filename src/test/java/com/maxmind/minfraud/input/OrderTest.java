package com.maxmind.minfraud.input;

import com.maxmind.minfraud.exception.InvalidInputException;
import com.maxmind.minfraud.input.Order.Builder;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URI;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testDoubleAmount() throws Exception {
        Order order = new Builder().amount(1.1).build();
        assertEquals(BigDecimal.valueOf(1.1), order.getAmount());
    }

    @Test
    public void testAmount() throws Exception {
        Order order = new Builder().amount(BigDecimal.valueOf(1.1)).build();
        assertEquals(BigDecimal.valueOf(1.1), order.getAmount());
    }

    @Test
    public void testCurrency() throws Exception {
        Order order = new Builder().currency("USD").build();
        assertEquals("USD", order.getCurrency());
    }

    @Test (expected = InvalidInputException.class)
    public void testCurrencyWithDigits() throws Exception {
         new Builder().currency("US1").build();
    }

    @Test (expected = InvalidInputException.class)
    public void testCurrencyThatIsTooShort() throws Exception {
        new Builder().currency("US").build();
    }


    @Test (expected = InvalidInputException.class)
    public void testCurrencyThatIsTooLong() throws Exception {
        new Builder().currency("USDE").build();
    }

    @Test (expected = InvalidInputException.class)
    public void testCurrencyInWrongCase() throws Exception {
        new Builder().currency("usd").build();
    }

    @Test
    public void testDiscountCode() throws Exception {
        Order order = new Builder().discountCode("dsc").build();
        assertEquals("dsc", order.getDiscountCode());
    }

    @Test
    public void testAffiliateId() throws Exception {
        Order order = new Builder().affiliateId("af").build();
        assertEquals("af", order.getAffiliateId());
    }

    @Test
    public void testSubaffiliateId() throws Exception {
        Order order = new Builder().subaffiliateId("saf").build();
        assertEquals("saf", order.getSubaffiliateId());
    }

    @Test
    public void testReferrerUri() throws Exception {
        URI uri = new URI("http://www.mm.com/");
        Order order = new Builder().referrerUri(uri).build();
        assertEquals(uri, order.getReferrerUri());
    }
}