package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The credit card information for the transaction.
 */
public final class CreditCard {

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
    private final Character avsResult;
    @JsonProperty("cvv_result")
    private final Character cvvResult;

    private CreditCard(CreditCard.Builder builder) {
        issuerIdNumber = builder.issuerIdNumber;
        last4Digits = builder.last4Digits;
        bankName = builder.bankName;
        bankPhoneCountryCode = builder.bankPhoneCountryCode;
        bankPhoneNumber = builder.bankPhoneNumber;
        avsResult = builder.avsResult;
        cvvResult = builder.cvvResult;
    }

    /**
     * {@code Builder} creates instances of the parent {@code CreditCard}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        String issuerIdNumber;
        String last4Digits;
        String bankName;
        String bankPhoneCountryCode;
        String bankPhoneNumber;
        Character avsResult;
        Character cvvResult;

        /**
         * @param number The issuer ID number for the credit card. This is the
         *               first 6 digits of the credit card number. It
         *               identifies the issuing bank.
         * @return The builder object.
         * @throws IllegalArgumentException when number is not a six digit
         *         string.
         */
        public CreditCard.Builder issuerIdNumber(String number) {
            if (!number.matches("[0-9]{6}")) {
                throw new IllegalArgumentException("The issuer ID number " + number + " is of the wrong format.");
            }
            issuerIdNumber = number;
            return this;
        }

        /**
         * @param digits The last four digits of the credit card number.
         * @return The builder object.
         * @throws IllegalArgumentException when number is not a four digit
         *         string.
         */
        public CreditCard.Builder last4Digits(String digits) {
            if (!digits.matches("[0-9]{4}")) {
                throw new IllegalArgumentException("The last 4 credit card digits " + digits + " are of the wrong format.");
            }
            last4Digits = digits;
            return this;
        }

        /**
         * @param name The name of the issuing bank as provided by the end user.
         * @return The builder object.
         */
        public CreditCard.Builder bankName(String name) {
            bankName = name;
            return this;
        }

        /**
         * @param code The phone country code for the issuing bank as provided
         *             by the end user.
         * @return The builder object.
         */
        public CreditCard.Builder bankPhoneCountryCode(String code) {
            bankPhoneCountryCode = code;
            return this;
        }

        /**
         * @param number The phone number, without the country code, for the
         *               issuing bank as provided by the end user.
         * @return The builder object.
         */
        public CreditCard.Builder bankPhoneNumber(String number) {
            bankPhoneNumber = number;
            return this;
        }

        /**
         * @param code The phone number, without the country code, for the
         *             issuing bank as provided by the end user.
         * @return The builder object.
         */
        public CreditCard.Builder avsResult(Character code) {
            avsResult = code;
            return this;
        }

        /**
         * @param code The card verification value (CVV) code as provided
         *             by the payment processor.
         * @return The builder object.
         */
        public CreditCard.Builder cvvResult(Character code) {
            cvvResult = code;
            return this;
        }

        /**
         * @return An instance of {@code CreditCard} created from the
         * fields set on this builder.
         */
        public CreditCard build() {
            return new CreditCard(this);
        }
    }

    /**
     * @return The issuer ID number.
     */
    public String getIssuerIdNumber() {
        return issuerIdNumber;
    }

    /**
     * @return The last 4 digits of the credit card number.
     */
    public String getLast4Digits() {
        return last4Digits;
    }

    /**
     * @return The name of the issuing bank as provided by the end user.
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @return The phone country code for the issuing bank as provided by
     * the end user.
     */
    public String getBankPhoneCountryCode() {
        return bankPhoneCountryCode;
    }

    /**
     * @return The phone number, without the country code, for the issuing
     * bank as provided by the end user.
     */
    public String getBankPhoneNumber() {
        return bankPhoneNumber;
    }

    /**
     * @return The address verification system (AVS) check result, as
     * returned to you by the credit card processor. The minFraud service
     * supports the standard AVS codes.
     */
    public Character getAvsResult() {
        return avsResult;
    }

    /**
     * @return The card verification value (CVV) code as provided by the
     * payment processor.
     */
    public Character getCvvResult() {
        return cvvResult;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CreditCard{");
        sb.append("issuerIdNumber='").append(this.issuerIdNumber).append('\'');
        sb.append(", last4Digits='").append(this.last4Digits).append('\'');
        sb.append(", bankName='").append(this.bankName).append('\'');
        sb.append(", bankPhoneCountryCode='").append(this.bankPhoneCountryCode).append('\'');
        sb.append(", bankPhoneNumber='").append(this.bankPhoneNumber).append('\'');
        sb.append(", avsResult=").append(this.avsResult);
        sb.append(", cvvResult=").append(this.cvvResult);
        sb.append('}');
        return sb.toString();
    }
}
