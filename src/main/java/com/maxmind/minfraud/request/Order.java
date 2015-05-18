package com.maxmind.minfraud.request;

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

    private Order(Order.Builder builder) {
        amount = builder.amount;
        currency = builder.currency;
        discountCode = builder.discountCode;
        affiliateId = builder.affiliateId;
        subaffiliateId = builder.subaffiliateId;
        referrerUri = builder.referrerUri;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order{");
        sb.append("amount=").append(this.amount);
        sb.append(", currency='").append(this.currency).append('\'');
        sb.append(", discountCode='").append(this.discountCode).append('\'');
        sb.append(", affiliateId='").append(this.affiliateId).append('\'');
        sb.append(", subaffiliateId='").append(this.subaffiliateId).append('\'');
        sb.append(", referrerUri=").append(this.referrerUri);
        sb.append('}');
        return sb.toString();
    }
}
