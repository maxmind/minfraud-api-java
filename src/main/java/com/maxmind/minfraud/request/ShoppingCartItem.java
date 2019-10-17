package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.math.BigDecimal;

/**
 * An item in the shopping cart.
 */
public final class ShoppingCartItem extends AbstractModel {
    private final String category;
    private final String itemId;
    private final Integer quantity;
    private final BigDecimal price;

    private ShoppingCartItem(ShoppingCartItem.Builder builder) {
        category = builder.category;
        itemId = builder.itemId;
        quantity = builder.quantity;
        price = builder.price;
    }

    /**
     * {@code Builder} creates instances of {@code ShippingCartItem}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        String category;
        String itemId;
        Integer quantity;
        BigDecimal price;

        /**
         * @param category The category of the item.
         * @return The builder object.
         */
        public ShoppingCartItem.Builder category(String category) {
            this.category = category;
            return this;
        }

        /**
         * @param id Your internal ID for the item
         * @return The builder object.
         */
        public ShoppingCartItem.Builder itemId(String id) {
            itemId = id;
            return this;
        }

        /**
         * @param quantity The quantity of the item in the shopping cart.
         * @return The builder object.
         * @throws IllegalArgumentException when quantity is not positive.
         */
        public ShoppingCartItem.Builder quantity(int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Expected positive quantity but received: " + quantity);
            }
            this.quantity = quantity;
            return this;
        }

        /**
         * @param price The per-unit price of the item in the shopping cart. This
         *              should use the same currency as the order currency.
         * @return The builder object.
         */
        public ShoppingCartItem.Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        /**
         * @param price The price of the item in the shopping cart. This
         *              should be the same currency as the order currency.
         * @return The builder object.
         */
        public ShoppingCartItem.Builder price(Double price) {
            this.price = BigDecimal.valueOf(price);
            return this;
        }

        /**
         * @return An instance of {@code ShoppingCartItem} created from the
         * fields set on this builder.
         */
        public ShoppingCartItem build() {
            return new ShoppingCartItem(this);
        }
    }

    /**
     * @return The category of the item.
     */
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    /**
     * @return The ID of the item.
     */
    @JsonProperty("item_id")
    public String getItemId() {
        return itemId;
    }

    /**
     * @return The quantity of the item.
     */
    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @return The price of the item.
     */
    @JsonProperty("price")
    public BigDecimal getPrice() {
        return price;
    }
}

