package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.maxmind.minfraud.request.ShoppingCartItem.Builder;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

    @Test
    public void testCategory() {
        ShoppingCartItem item = new Builder().category("cat1").build();
        assertEquals("cat1", item.category());
    }

    @Test
    public void testItemId() {
        ShoppingCartItem item = new Builder().itemId("id5").build();
        assertEquals("id5", item.itemId());
    }

    @Test
    public void testQuantity() {
        ShoppingCartItem item = new Builder().quantity(100).build();
        assertEquals(Integer.valueOf(100), item.quantity());
    }

    @Test
    public void testPrice() {
        ShoppingCartItem item = new Builder().price(BigDecimal.TEN).build();
        assertEquals(BigDecimal.TEN, item.price());
    }

    @Test
    public void testDoublePrice() {
        ShoppingCartItem item = new Builder().price(10.3).build();
        assertEquals(BigDecimal.valueOf(10.3), item.price());
    }
}