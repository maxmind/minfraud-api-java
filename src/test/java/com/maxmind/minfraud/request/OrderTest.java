package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.maxmind.minfraud.request.Order.Builder;
import java.math.BigDecimal;
import java.net.URI;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void testDoubleAmount() {
        Order order = new Builder().amount(1.1).build();
        assertEquals(BigDecimal.valueOf(1.1), order.amount());
    }

    @Test
    public void testAmount() {
        Order order = new Builder().amount(BigDecimal.valueOf(1.1)).build();
        assertEquals(BigDecimal.valueOf(1.1), order.amount());
    }

    @Test
    public void testCurrency() {
        Order order = new Builder().currency("USD").build();
        assertEquals("USD", order.currency());
    }

    @Test
    public void testCurrencyWithDigits() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().currency("US1").build()
        );
    }

    @Test
    public void testCurrencyThatIsTooShort() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().currency("US").build()
        );
    }


    @Test
    public void testCurrencyThatIsTooLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().currency("USDE").build()
        );
    }

    @Test
    public void testCurrencyInWrongCase() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder().currency("usd").build()
        );
    }

    @Test
    public void testDiscountCode() {
        Order order = new Builder().discountCode("dsc").build();
        assertEquals("dsc", order.discountCode());
    }

    @Test
    public void testAffiliateId() {
        Order order = new Builder().affiliateId("af").build();
        assertEquals("af", order.affiliateId());
    }

    @Test
    public void testSubaffiliateId() {
        Order order = new Builder().subaffiliateId("saf").build();
        assertEquals("saf", order.subaffiliateId());
    }

    @Test
    public void testReferrerUri() throws Exception {
        URI uri = new URI("http://www.mm.com/");
        Order order = new Builder().referrerUri(uri).build();
        assertEquals(uri, order.referrerUri());
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