package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Order.Builder;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderTest {

    @Test
    public void testDoubleAmount() {
        Order order = new Builder().amount(1.1).build();
        assertEquals(BigDecimal.valueOf(1.1), order.getAmount());
    }

    @Test
    public void testAmount() {
        Order order = new Builder().amount(BigDecimal.valueOf(1.1)).build();
        assertEquals(BigDecimal.valueOf(1.1), order.getAmount());
    }

    @Test
    public void testCurrency() {
        Order order = new Builder().currency("USD").build();
        assertEquals("USD", order.getCurrency());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyWithDigits() {
        new Builder().currency("US1").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyThatIsTooShort() {
        new Builder().currency("US").build();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyThatIsTooLong() {
        new Builder().currency("USDE").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCurrencyInWrongCase() {
        new Builder().currency("usd").build();
    }

    @Test
    public void testDiscountCode() {
        Order order = new Builder().discountCode("dsc").build();
        assertEquals("dsc", order.getDiscountCode());
    }

    @Test
    public void testAffiliateId() {
        Order order = new Builder().affiliateId("af").build();
        assertEquals("af", order.getAffiliateId());
    }

    @Test
    public void testSubaffiliateId() {
        Order order = new Builder().subaffiliateId("saf").build();
        assertEquals("saf", order.getSubaffiliateId());
    }

    @Test
    public void testReferrerUri() throws Exception {
        URI uri = new URI("http://www.mm.com/");
        Order order = new Builder().referrerUri(uri).build();
        assertEquals(uri, order.getReferrerUri());
    }

    @Test
    public void testIsGift() {
        Order order = new Builder().isGift(true).build();
        assertTrue(order.isGift());
    }

    @Test
    public void testHasGiftMessage() {
        Order order = new Builder().hasGiftMessage(true).build();
        assertTrue(order.hasGiftMessage());
    }
}