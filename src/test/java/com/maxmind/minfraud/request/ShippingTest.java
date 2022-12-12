package com.maxmind.minfraud.request;

import static org.junit.Assert.assertEquals;

import com.maxmind.minfraud.request.Shipping.Builder;
import com.maxmind.minfraud.request.Shipping.DeliverySpeed;
import org.junit.Test;

public class ShippingTest extends AbstractLocationTest {

    Builder builder() {
        return new Builder();
    }

    @Test
    public void testDeliverySpeed() {
        Shipping loc = this.builder().deliverySpeed(DeliverySpeed.EXPEDITED).build();
        assertEquals(DeliverySpeed.EXPEDITED, loc.getDeliverySpeed());
    }
}