package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The shipping information for the transaction.
 */
public final class Shipping extends AbstractLocation {
    @JsonProperty("delivery_speed")
    private final DeliverySpeed deliverySpeed;

    private Shipping(Shipping.Builder builder) {
        super(builder);
        deliverySpeed = builder.deliverySpeed;
    }

    /**
     * {@code Builder} creates instances of {@code Shipping}
     * from values set by the builder's methods.
     */
    public static final class Builder extends AbstractLocation.Builder<Shipping.Builder> {
        DeliverySpeed deliverySpeed;

        /**
         * @param speed The shipping delivery speed for the order.
         * @return The builder object.
         */
        public Shipping.Builder deliverySpeed(DeliverySpeed speed) {
            deliverySpeed = speed;
            return this;
        }

        /**
         * @return An instance of {@code Shipping} created from the
         * fields set on this builder.
         */
        @Override
        public Shipping build() {
            return new Shipping(this);
        }
    }

    public DeliverySpeed getDeliverySpeed() {
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

    /**
     * Enumerated delivery speeds.
     */
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
