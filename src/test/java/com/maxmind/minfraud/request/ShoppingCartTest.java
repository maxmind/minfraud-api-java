package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.ShoppingCartItem.Builder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    @Test
    public void testCategory() {
        ShoppingCartItem item = new Builder().category("cat1").build();
        assertEquals("cat1", item.getCategory());
    }

    @Test
    public void testItemId() {
        ShoppingCartItem item = new Builder().itemId("id5").build();
        assertEquals("id5", item.getItemId());
    }

    @Test
    public void testQuantity() {
        ShoppingCartItem item = new Builder().quantity(100).build();
        assertEquals(Integer.valueOf(100), item.getQuantity());
    }

    @Test
    public void testPrice() {
        ShoppingCartItem item = new Builder().price(BigDecimal.TEN).build();
        assertEquals(BigDecimal.TEN, item.getPrice());
    }

    @Test
    public void testDoublePrice() {
        ShoppingCartItem item = new Builder().price(10.3).build();
        assertEquals(BigDecimal.valueOf(10.3), item.getPrice());
    }
}