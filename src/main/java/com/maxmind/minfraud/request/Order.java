package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.math.BigDecimal;
import java.net.URI;
import java.util.regex.Pattern;

/**
 * The order information for the transaction.
 */
public final class Order extends AbstractModel {
    private final BigDecimal amount;
    private final String currency;
    private final String discountCode;
    private final String affiliateId;
    private final String subaffiliateId;
    private final URI referrerUri;
    private final Boolean isGift;
    private final Boolean hasGiftMessage;

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
        private static final Pattern CURRENCY_CODE_PATTERN = Pattern.compile("^[A-Z]{3}$");

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
            this.amount = BigDecimal.valueOf(amount);
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
            if (!CURRENCY_CODE_PATTERN.matcher(code).matches()) {
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
    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return The currency code.
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * @return The discount codes.
     */
    @JsonProperty("discount_code")
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @return The affiliate ID.
     */
    @JsonProperty("affiliate_id")
    public String getAffiliateId() {
        return affiliateId;
    }

    /**
     * @return The sub-affiliate ID.
     */
    @JsonProperty("subaffiliate_id")
    public String getSubaffiliateId() {
        return subaffiliateId;
    }

    /**
     * @return The referrer URI.
     */
    @JsonProperty("referrer_uri")
    public URI getReferrerUri() {
        return referrerUri;
    }

    /**
     * @return The order is a gift.
     */
    @JsonProperty("has_gift_message")
    public Boolean hasGiftMessage() {
        return hasGiftMessage;
    }

    /**
     * @return The order is a gift.
     */
    @JsonProperty("is_gift")
    public Boolean isGift() {
        return isGift;
    }
}
