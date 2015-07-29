package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.net.URI;

/**
 * The order information for the transaction.
 */
public final class Order {
    @JsonProperty("amount")
    private final BigDecimal amount;
    @JsonProperty
    private final String currency;
    @JsonProperty("discount_code")
    private final String discountCode;
    @JsonProperty("affiliate_id")
    private final String affiliateId;
    @JsonProperty("subaffiliate_id")
    private final String subaffiliateId;
    @JsonProperty("referrer_uri")
    private final URI referrerUri;
    @JsonProperty("is_gift")
    private Boolean isGift;
    @JsonProperty("has_gift_message")
    private Boolean hasGiftMessage;

    private Order(Order.Builder builder) {
        amount = builder.amount;
        currency = builder.currency;
        discountCode = builder.discountCode;
        affiliateId = builder.affiliateId;
        subaffiliateId = builder.subaffiliateId;
        referrerUri = builder.referrerUri;
        isGift = builder.isGift;
        hasGiftMessage = builder.hasGiftMessage;
    }

    /**
     * {@code Builder} creates instances of {@code Order}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        BigDecimal amount;
        String currency;
        String discountCode;
        String affiliateId;
        String subaffiliateId;
        URI referrerUri;
        private Boolean isGift;
        private Boolean hasGiftMessage;

        /**
         * @param amount The total order amount for the transaction.
         * @return The builder object.
         */
        public Order.Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * @param amount The total order amount for the transaction.
         * @return The builder object.
         */
        public Order.Builder amount(Double amount) {
            this.amount = BigDecimal.valueOf(amount.doubleValue());
            return this;
        }

        /**
         * @param code The ISO 4217 currency code for the currency used in the
         *             transaction.
         * @return The builder object.
         * @throws IllegalArgumentException when currency is not a valid
         *                                  three-letter currency code.
         */
        public Order.Builder currency(String code) {
            if (!code.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException("The currency code " + code + " is invalid.");
            }
            currency = code;
            return this;
        }

        /**
         * @param code The discount code applied to the transaction. If
         *             multiple discount codes were used, please
         *             separate them with a comma.
         * @return The builder object.
         */
        public Order.Builder discountCode(String code) {
            discountCode = code;
            return this;
        }

        /**
         * @param id The ID of the affiliate where the order is coming from.
         * @return The builder object.
         */
        public Order.Builder affiliateId(String id) {
            affiliateId = id;
            return this;
        }

        /**
         * @param isGift Whether order was marked as a gift by the purchaser.
         * @return The builder object.
         */
        public Order.Builder isGift(boolean isGift) {
            this.isGift = isGift;
            return this;
        }

        /**
         * @param hasGiftMessage Whether the purchaser included a gift message.
         * @return The builder object.
         */
        public Order.Builder hasGiftMessage(boolean hasGiftMessage) {
            this.hasGiftMessage = hasGiftMessage;
            return this;
        }

        /**
         * @param id The ID of the sub-affiliate where the order is coming
         *           from.
         * @return The builder object.
         */
        public Order.Builder subaffiliateId(String id) {
            subaffiliateId = id;
            return this;
        }

        /**
         * @param uri The URI of the referring site for this order.
         * @return The builder object.
         */
        public Order.Builder referrerUri(URI uri) {
            referrerUri = uri;
            return this;
        }

        /**
         * @return An instance of {@code Order} created from the
         * fields set on this builder.
         */
        public Order build() {
            return new Order(this);
        }
    }

    /**
     * @return The total order amount.
     */
    public final BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return The currency code.
     */
    public final String getCurrency() {
        return currency;
    }

    /**
     * @return The discount codes.
     */
    public final String getDiscountCode() {
        return discountCode;
    }

    /**
     * @return The affiliate ID.
     */
    public final String getAffiliateId() {
        return affiliateId;
    }

    /**
     * @return The sub-affiliate ID.
     */
    public final String getSubaffiliateId() {
        return subaffiliateId;
    }

    /**
     * @return The referrer URI.
     */
    public final URI getReferrerUri() {
        return referrerUri;
    }

    /**
     * @return The order is a gift.
     */
    @JsonIgnore
    public final Boolean hasGiftMessage() {
        return hasGiftMessage;
    }

    /**
     * @return The order is a gift.
     */
    @JsonIgnore
    public final Boolean isGift() {
        return isGift;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("amount=").append(amount);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", discountCode='").append(discountCode).append('\'');
        sb.append(", affiliateId='").append(affiliateId).append('\'');
        sb.append(", subaffiliateId='").append(subaffiliateId).append('\'');
        sb.append(", referrerUri=").append(referrerUri);
        sb.append(", isGift=").append(isGift);
        sb.append(", hasGiftMessage=").append(hasGiftMessage);
        sb.append('}');
        return sb.toString();
    }
}
