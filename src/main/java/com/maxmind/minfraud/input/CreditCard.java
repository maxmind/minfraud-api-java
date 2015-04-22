package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.exception.InvalidInputException;

/**
 * The credit card information for the transaction.
 */
public class CreditCard {

    @JsonProperty("issuer_id_number")
    private final String issuerIdNumber;

    @JsonProperty("last_4_digits")
    private final String last4Digits;

    @JsonProperty("bank_name")
    private final String bankName;

    @JsonProperty("bank_phone_country_code")
    private final String bankPhoneCountryCode;

    @JsonProperty("bank_phone_number")
    private final String bankPhoneNumber;

    @JsonProperty("avs_result")
    private final char avsResult;

    @JsonProperty("cvv_result")
    private final char cvvResult;

    private CreditCard(Builder builder) {
        this.issuerIdNumber = builder.issuerIdNumber;
        this.last4Digits = builder.last4Digits;
        this.bankName = builder.bankName;
        this.bankPhoneCountryCode = builder.bankPhoneCountryCode;
        this.bankPhoneNumber = builder.bankPhoneNumber;
        this.avsResult = builder.avsResult;
        this.cvvResult = builder.cvvResult;
    }

    public final static class Builder {
        String issuerIdNumber;
        String last4Digits;
        String bankName;
        String bankPhoneCountryCode;
        String bankPhoneNumber;
        char avsResult;
        char cvvResult;

        public Builder issuerIdNumber(String number) {
            if (!number.matches("[0-9]{6}")) {
                throw new InvalidInputException("The issuer ID number " + number + " is of the wrong format.");
            }
            this.issuerIdNumber = number;
            return this;
        }

        public Builder last4Digits(String digits) {
            if (!digits.matches("[0-9]{4}")) {
                throw new InvalidInputException("The last 4 credit card digits " + digits + " are of the wrong format.");
            }
            this.last4Digits = digits;
            return this;
        }

        public Builder bankName(String name) {
            this.bankName = name;
            return this;
        }

        public Builder bankPhoneCountryCode(String code) {
            this.bankPhoneCountryCode = code;
            return this;
        }

        public Builder bankPhoneNumber(String number) {
            this.bankPhoneNumber = number;
            return this;
        }

        public Builder avsResult(char code) {
            this.avsResult = code;
            return this;
        }

        public Builder cvvResult(char code) {
            this.cvvResult = code;
            return this;
        }

        public CreditCard build() {
            return new CreditCard(this);
        }
    }
}
