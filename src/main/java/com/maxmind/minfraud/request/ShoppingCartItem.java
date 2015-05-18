package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final Integer quantity;
    @JsonProperty
    private final BigDecimal price;

    public ShoppingCartItem(ShoppingCartItem.Builder builder) {
        category = builder.category;
        itemId = builder.itemId;
        quantity = builder.quantity;
        price = builder.price;
    }

    public static final class Builder {
        String category;
        String itemId;
        Integer quantity;
        BigDecimal price;

        public ShoppingCartItem.Builder category(String category) {
            this.category = category;
            return this;
        }

        public ShoppingCartItem.Builder itemId(String id) {
            itemId = id;
            return this;
        }

        public ShoppingCartItem.Builder quantity(int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Expected positive quantity but received: " + Integer.toString(quantity));
            }
            this.quantity = quantity;
            return this;
        }

        public ShoppingCartItem.Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ShoppingCartItem.Builder price(Double price) {
            this.price = BigDecimal.valueOf(price.doubleValue());
            return this;
        }

        public ShoppingCartItem build() {
            return new ShoppingCartItem(this);
        }
    }

    public final String getCategory() {
        return category;
    }

    public final String getItemId() {
        return itemId;
    }

    public final Integer getQuantity() {
        return quantity;
    }

    public final BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ShoppingCartItem{");
        sb.append("category='").append(this.category).append('\'');
        sb.append(", itemId='").append(this.itemId).append('\'');
        sb.append(", quantity=").append(this.quantity);
        sb.append(", price=").append(this.price);
        sb.append('}');
        return sb.toString();
    }
}

