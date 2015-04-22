package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.MinFraudRequest;
import com.maxmind.minfraud.exception.InvalidInputException;
import org.apache.commons.validator.routines.UrlValidator;

import java.math.BigDecimal;

/**
 * The order information for the transaction.
 */
public class Order {
    @JsonProperty("amount")
    private BigDecimal amount;
    private String currency;
    @JsonProperty("discount_code")
    private String discountCode;
    @JsonProperty("affiliate_id")
    private String affiliateId;
    @JsonProperty("subaffiliate_id")
    private String subaffiliateId;
    @JsonProperty("referrer_uri")
    private String referrerUri;

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
        String referrerUri;

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
            this.currency = currency;
            return this;
        }

        public Builder discountCode(String code) {
            this.discountCode = discountCode;
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

        public Builder referrerUri(String uri) {
            if (!UrlValidator.getInstance().isValid(uri)) {
                throw new InvalidInputException("The referrer URI " + uri + " is invalid.");
            }
            this.referrerUri = uri;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
