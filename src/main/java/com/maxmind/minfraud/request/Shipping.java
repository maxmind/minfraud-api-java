package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The shipping information for the transaction.
 */
public class Shipping extends AbstractLocation {
    @JsonProperty("delivery_speed")
    private final DeliverySpeed deliverySpeed;

    private Shipping(Shipping.Builder builder) {
        super(builder);
        deliverySpeed = builder.deliverySpeed;
    }

    public static final class Builder extends AbstractLocation.Builder<Shipping.Builder> {
        DeliverySpeed deliverySpeed;

        public Shipping.Builder deliverySpeed(DeliverySpeed speed) {
            deliverySpeed = speed;
            return this;
        }

        public Shipping build() {
            return new Shipping(this);
        }
    }

    public final DeliverySpeed getDeliverySpeed() {
        return deliverySpeed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Shipping{");
        sb.append("deliverySpeed=").append(this.deliverySpeed);
        sb.append(", super:").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public enum DeliverySpeed {
        SAME_DAY,
        OVERNIGHT,
        EXPEDITED,
        STANDARD;

        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
