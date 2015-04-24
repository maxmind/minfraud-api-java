package com.maxmind.minfraud.request;

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

    public static final class Builder {
        String issuerIdNumber;
        String last4Digits;
        String bankName;
        String bankPhoneCountryCode;
        String bankPhoneNumber;
        Character avsResult;
        Character cvvResult;

        public CreditCard.Builder issuerIdNumber(String number) {
            if (!number.matches("[0-9]{6}")) {
                throw new InvalidInputException("The issuer ID number " + number + " is of the wrong format.");
            }
            issuerIdNumber = number;
            return this;
        }

        public CreditCard.Builder last4Digits(String digits) {
            if (!digits.matches("[0-9]{4}")) {
                throw new InvalidInputException("The last 4 credit card digits " + digits + " are of the wrong format.");
            }
            last4Digits = digits;
            return this;
        }

        public CreditCard.Builder bankName(String name) {
            bankName = name;
            return this;
        }

        public CreditCard.Builder bankPhoneCountryCode(String code) {
            bankPhoneCountryCode = code;
            return this;
        }

        public CreditCard.Builder bankPhoneNumber(String number) {
            bankPhoneNumber = number;
            return this;
        }

        public CreditCard.Builder avsResult(Character code) {
            avsResult = code;
            return this;
        }

        public CreditCard.Builder cvvResult(Character code) {
            cvvResult = code;
            return this;
        }

        public CreditCard build() {
            return new CreditCard(this);
        }
    }

    public final String getIssuerIdNumber() {
        return issuerIdNumber;
    }

    public final String getLast4Digits() {
        return last4Digits;
    }

    public final String getBankName() {
        return bankName;
    }

    public final String getBankPhoneCountryCode() {
        return bankPhoneCountryCode;
    }

    public final String getBankPhoneNumber() {
        return bankPhoneNumber;
    }

    public final Character getAvsResult() {
        return avsResult;
    }

    public final Character getCvvResult() {
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
