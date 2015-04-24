package com.maxmind.minfraud.input;

import com.maxmind.minfraud.input.Shipping.Builder;
import com.maxmind.minfraud.input.Shipping.DeliverySpeed;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShippingTest extends AbstractLocationTest {

    Builder builder() {
        return new Builder();
    }

    @Test
    public void testDeliverySpeed() throws Exception {
        Shipping loc = this.builder().deliverySpeed(DeliverySpeed.EXPEDITED).build();
        assertEquals(DeliverySpeed.EXPEDITED, loc.getDeliverySpeed());
    }
}