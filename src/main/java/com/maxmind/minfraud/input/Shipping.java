package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The shipping information for the transaction.
 */
public class Shipping extends AbstractLocation {
    @JsonProperty("delivery_speed")
    private Shipping.DeliverySpeed deliverySpeed;

    private Shipping(Builder builder) {
        super(builder);
        this.deliverySpeed = builder.deliverySpeed;
    }

    public Shipping.DeliverySpeed getDeliverySpeed() {
        return this.deliverySpeed;
    }

    public enum DeliverySpeed {
        SAME_DAY,
        OVERNIGHT,
        EXPEDITED,
        STANDARD;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public final static class Builder extends AbstractLocation.Builder<Builder> {
        Shipping.DeliverySpeed deliverySpeed;

        public Builder deliverySpeed(Shipping.DeliverySpeed speed) {
            this.deliverySpeed = speed;
            return this;
        }

        public Shipping build() {
            return new Shipping(this);
        }
    }
}
