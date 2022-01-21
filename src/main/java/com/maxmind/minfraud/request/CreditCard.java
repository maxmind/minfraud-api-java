package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.util.regex.Pattern;

/**
 * The credit card information for the transaction.
 */
public final class CreditCard extends AbstractModel {
    private final String issuerIdNumber;
    private final String lastDigits;
    private final String bankName;
    private final String bankPhoneCountryCode;
    private final String bankPhoneNumber;
    private final Character avsResult;
    private final Character cvvResult;
    private final String token;
    private final Boolean was3dSecureSuccessful;

    private CreditCard(CreditCard.Builder builder) {
        issuerIdNumber = builder.issuerIdNumber;
        lastDigits = builder.lastDigits;
        bankName = builder.bankName;
        bankPhoneCountryCode = builder.bankPhoneCountryCode;
        bankPhoneNumber = builder.bankPhoneNumber;
        avsResult = builder.avsResult;
        cvvResult = builder.cvvResult;
        token = builder.token;
        was3dSecureSuccessful = builder.was3dSecureSuccessful;
    }

    /**
     * {@code Builder} creates instances of the parent {@code CreditCard}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        private static final Pattern IIN_PATTERN = Pattern.compile("^(?:[0-9]{6}|[0-9]{8})$");
        private static final Pattern LAST_DIGITS_PATTERN = Pattern.compile("^(?:[0-9]{2}|[0-9]{4})$");
        private static final Pattern TOKEN_PATTERN = Pattern.compile("^(?![0-9]{1,19}$)[\\x21-\\x7E]{1,255}$");

        String issuerIdNumber;
        String lastDigits;
        String bankName;
        String bankPhoneCountryCode;
        String bankPhoneNumber;
        String token;
        Character avsResult;
        Character cvvResult;
        Boolean was3dSecureSuccessful;

        /**
         * @param number The issuer ID number for the credit card. This is the
         *               first 6 or 8 digits of the credit card number. It
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
         * @deprecated
         * Use lastDigits instead.
         *
         * @param digits The last two or four digits of the credit card number.
         * @return The builder object.
         * @throws IllegalArgumentException when number is not a two or four digit
         *                                  string.
         */
        @Deprecated
        public CreditCard.Builder last4Digits(String digits) {
            return this.lastDigits(digits);
        }

        /**
         * @param digits The last two or four digits of the credit card number.
         * @return The builder object.
         * @throws IllegalArgumentException when number is not a two or four digit
         *                                  string.
         */
        public CreditCard.Builder lastDigits(String digits) {
            if (!LAST_DIGITS_PATTERN.matcher(digits).matches()) {
                throw new IllegalArgumentException("The last credit card digits " + digits + " are of the wrong format.");
            }
            lastDigits = digits;
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
         * @param code The address verification system (AVS) check result, as
         *             returned to you by the credit card processor. The
         *             minFraud service supports the standard AVS codes.
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
         * @param token A token uniquely identifying the card. The token
         *              should consist of non-space printable ASCII
         *              characters. If the token is all digits, it must be
         *              more than 19 characters long. The token must not be a
         *              primary account number (PAN) or a simple
         *              transformation of it. If you have a valid token that
         *              looks like a PAN but is not one, you may prefix that
         *              token with a fixed string, e.g., "token-".
         * @return The builder object.
         * @throws IllegalArgumentException when the token is invalid.
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
         * @param wasSuccessful An indication of whether or not the outcome of
         *                      3D-Secure verification (e.g. Safekey,
         *                      SecureCode, Verified by Visa)  was successful,
         *                      as provided by the end user. {@code true} if
         *                      customer verification was successful, or
         *                      {@code false} if the customer failed
         *                      verification. If 3-D Secure verification was not
         *                      used, was unavailable, or resulted in another
         *                      outcome other than success or failure, do not
         *                      use this method.
         * @return The builder object.
         */
        public CreditCard.Builder was3dSecureSuccessful(Boolean wasSuccessful) {
            this.was3dSecureSuccessful = wasSuccessful;
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
     * @deprecated
     *
     * @return The last two or four digits of the credit card number.
     */
    @Deprecated
    @JsonIgnore
    public String getLast4Digits() {
        return this.getLastDigits();
    }

    /**
     * @return The last two or four digits of the credit card number.
     */
    @JsonProperty("last_digits")
    public String getLastDigits() {
        return lastDigits;
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

    /**
     * @return An indication of whether or not the outcome of 3D-Secure
     * verification (e.g. Safekey, SecureCode, Verified by Visa) was
     * successful, as provided by the end user. {@code true} if customer
     * verification was successful, or {@code false} if the customer
     * failed verification. {@code null} if 3-D Secure verification was
     * not used, was unavailable, or resulted in another outcome other
     * than success or failure.
     */
    @JsonProperty("was_3d_secure_successful")
    public Boolean getWas3dSecureSuccessful() {
        return was3dSecureSuccessful;
    }
}
