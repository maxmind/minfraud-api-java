package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.exception.InvalidInputException;
import org.apache.commons.validator.routines.UrlValidator;

import java.math.BigDecimal;
import java.net.URI;

/**
 * The order information for the transaction.
 */
public class Order {
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty
    private String currency;
    @JsonProperty("discount_code")
    private String discountCode;
    @JsonProperty("affiliate_id")
    private String affiliateId;
    @JsonProperty("subaffiliate_id")
    private String subaffiliateId;
    @JsonProperty("referrer_uri")
    private URI referrerUri;

    public URI getReferrerUri() {
        return this.referrerUri;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }

    public String getAffiliateId() {
        return this.affiliateId;
    }

    public String getSubaffiliateId() {
        return this.subaffiliateId;
    }

    public Order(Builder builder) {
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.discountCode = builder.discountCode;
        this.affiliateId = builder.affiliateId;
        this.subaffiliateId = builder.subaffiliateId;
        this.referrerUri = builder.referrerUri;
    }

    public final static class Builder {
        BigDecimal amount;
        String currency;
        String discountCode;
        String affiliateId;
        String subaffiliateId;
        URI referrerUri;

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = BigDecimal.valueOf(amount);
            return this;
        }

        public Builder currency(String code) {
            if (!code.matches("[A-Z]{3}")) {
                throw new InvalidInputException("The currency code " + code + " is invalid.");
            }
            this.currency = code;
            return this;
        }

        public BigDecimal getAmount() {
            return this.amount;
        }

        public Builder discountCode(String code) {
            this.discountCode = code;
            return this;
        }

        public Builder affiliateId(String id) {
            this.affiliateId = id;
            return this;
        }

        public Builder subaffiliateId(String id) {
            this.subaffiliateId = id;
            return this;
        }

        public Builder referrerUri(URI uri) {
            this.referrerUri = uri;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
