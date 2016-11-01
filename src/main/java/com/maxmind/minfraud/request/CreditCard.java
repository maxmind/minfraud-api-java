package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.util.regex.Pattern;

/**
 * The credit card information for the transaction.
 */
public final class CreditCard extends AbstractModel {
    private final String issuerIdNumber;
    private final String last4Digits;
    private final String bankName;
    private final String bankPhoneCountryCode;
    private final String bankPhoneNumber;
    private final Character avsResult;
    private final Character cvvResult;
    private final String token;

    private CreditCard(CreditCard.Builder builder) {
        issuerIdNumber = builder.issuerIdNumber;
        last4Digits = builder.last4Digits;
        bankName = builder.bankName;
        bankPhoneCountryCode = builder.bankPhoneCountryCode;
        bankPhoneNumber = builder.bankPhoneNumber;
        avsResult = builder.avsResult;
        cvvResult = builder.cvvResult;
        token = builder.token;
    }

    /**
     * {@code Builder} creates instances of the parent {@code CreditCard}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        private static final Pattern IIN_PATTERN = Pattern.compile("^[0-9]{6}$");
        private static final Pattern LAST_4_PATTERN = Pattern.compile("^[0-9]{4}$");
        private static final Pattern TOKEN_PATTERN = Pattern.compile("^(?![0-9]{1,19}$)[\\x21-\\x7E]{1,255}$");

        String issuerIdNumber;
        String last4Digits;
        String bankName;
        String bankPhoneCountryCode;
        String bankPhoneNumber;
        String token;
        Character avsResult;
        Character cvvResult;

        /**
         * @param number The issuer ID number for the credit card. This is the
         *               first 6 digits of the credit card number. It
         *               identifies the issuing bank.
         * @return The builder object.
         * @throws IllegalArgumentException when number is not a six digit
         *                                  string.
         */
        public CreditCard.Builder issuerIdNumber(String number) {
            if (!IIN_PATTERN.matcher(number).matches()) {
                throw new IllegalArgumentException("The issuer ID number " + number + " is of the wrong format.");
            }
            issuerIdNumber = number;
            return this;
        }

        /**
         * @param digits The last four digits of the credit card number.
         * @return The builder object.
         * @throws IllegalArgumentException when number is not a four digit
         *                                  string.
         */
        public CreditCard.Builder last4Digits(String digits) {
            if (!LAST_4_PATTERN.matcher(digits).matches()) {
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
         * @param token A token uniquely identifying the card. This should not be
         *             the actual credit card number.
         * @return The builder object.
         * @throws IllegalArgumentException when the token is invalid.
         *
         */
        public CreditCard.Builder token(String token) {
            if (!TOKEN_PATTERN.matcher(token).matches()) {
                throw new IllegalArgumentException("The credit card token was invalid. "
                        + "Tokens must be non-space ASCII printable characters. If the "
                        + "token consists of all digits, it must be more than 19 digits.");
            }
            this.token = token;
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
    @JsonProperty("issuer_id_number")
    public String getIssuerIdNumber() {
        return issuerIdNumber;
    }

    /**
     * @return The last 4 digits of the credit card number.
     */
    @JsonProperty("last_4_digits")
    public String getLast4Digits() {
        return last4Digits;
    }

    /**
     * @return The name of the issuing bank as provided by the end user.
     */
    @JsonProperty("bank_name")
    public String getBankName() {
        return bankName;
    }

    /**
     * @return The phone country code for the issuing bank as provided by
     * the end user.
     */
    @JsonProperty("bank_phone_country_code")
    public String getBankPhoneCountryCode() {
        return bankPhoneCountryCode;
    }

    /**
     * @return The phone number, without the country code, for the issuing
     * bank as provided by the end user.
     */
    @JsonProperty("bank_phone_number")
    public String getBankPhoneNumber() {
        return bankPhoneNumber;
    }

    /**
     * @return The address verification system (AVS) check result, as
     * returned to you by the credit card processor. The minFraud service
     * supports the standard AVS codes.
     */
    @JsonProperty("avs_result")
    public Character getAvsResult() {
        return avsResult;
    }

    /**
     * @return The card verification value (CVV) code as provided by the
     * payment processor.
     */
    @JsonProperty("cvv_result")
    public Character getCvvResult() {
        return cvvResult;
    }

    /**
     * @return A credit card token uniquely identifying the card.
     */
    @JsonProperty("token")
    public String getToken() {
        return token;
    }
}
