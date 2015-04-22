package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.MinFraudRequest;
import com.maxmind.minfraud.exception.InvalidInputException;

import java.math.BigDecimal;

/**
 * An item in the shopping cart.
 */
public class ShoppingCartItem {
    @JsonProperty
    private final String category;

    @JsonProperty("item_id")
    private final String itemId;

    @JsonProperty
    private final int quantity;
    @JsonProperty
    private final BigDecimal price;

    public ShoppingCartItem(Builder builder) {
        this.category = builder.category;
        this.itemId = builder.itemId;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public final static class Builder {
        String category;
        String itemId;
        int quantity;
        BigDecimal price;

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder itemId(String id) {
            this.itemId = id;
            return this;
        }

        public Builder quantity(int quantity) {
            if (quantity <= 0) {
                throw new InvalidInputException("Expected positive quantity but received: " + Integer.toString(quantity));
            }
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder price(Double price) {
            this.price = BigDecimal.valueOf(price);
            return this;
        }

        public ShoppingCartItem build() {
            return new ShoppingCartItem(this);
        }
    }
}

