package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.net.URI;

/**
 * The order information for the transaction.
 */
public class Order {
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

    public Order(Order.Builder builder) {
        amount = builder.amount;
        currency = builder.currency;
        discountCode = builder.discountCode;
        affiliateId = builder.affiliateId;
        subaffiliateId = builder.subaffiliateId;
        referrerUri = builder.referrerUri;
    }

    public static final class Builder {
        BigDecimal amount;
        String currency;
        String discountCode;
        String affiliateId;
        String subaffiliateId;
        URI referrerUri;

        public Order.Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Order.Builder amount(Double amount) {
            this.amount = BigDecimal.valueOf(amount.doubleValue());
            return this;
        }

        public Order.Builder currency(String code) {
            if (!code.matches("[A-Z]{3}")) {
                throw new IllegalArgumentException("The currency code " + code + " is invalid.");
            }
            currency = code;
            return this;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public Order.Builder discountCode(String code) {
            discountCode = code;
            return this;
        }

        public Order.Builder affiliateId(String id) {
            affiliateId = id;
            return this;
        }

        public Order.Builder subaffiliateId(String id) {
            subaffiliateId = id;
            return this;
        }

        public Order.Builder referrerUri(URI uri) {
            referrerUri = uri;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public final URI getReferrerUri() {
        return referrerUri;
    }

    public final BigDecimal getAmount() {
        return amount;
    }

    public final String getCurrency() {
        return currency;
    }

    public final String getDiscountCode() {
        return discountCode;
    }

    public final String getAffiliateId() {
        return affiliateId;
    }

    public final String getSubaffiliateId() {
        return subaffiliateId;
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
