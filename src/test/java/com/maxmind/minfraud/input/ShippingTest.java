package com.maxmind.minfraud.input;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShippingTest extends AbstractLocationTest {

    Shipping.Builder builder() {
        return new Shipping.Builder();
    }

    @Test
    public void testDeliverySpeed() throws Exception {
        Shipping loc = builder().deliverySpeed(Shipping.DeliverySpeed.EXPEDITED).build();
        assertEquals(Shipping.DeliverySpeed.EXPEDITED, loc.getDeliverySpeed());
    }
}