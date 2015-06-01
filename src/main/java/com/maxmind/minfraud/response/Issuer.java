package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the credit card issuer.
 */
public final class Issuer {
    protected String name;

    @JsonProperty("matches_provided_name")
    protected Boolean matchesProvidedName;

    @JsonProperty("phone_number")
    protected String phoneNumber;

    @JsonProperty("matches_provided_phone_number")
    protected Boolean matchesProvidedPhoneNumber;

    /**
     * @return The name of the bank which issued the credit card.
     */
    public final String getName() {
        return name;
    }

    /**
     * @return This returns true if the name matches the name provided in the
     * request for the card issuer. It returns false if the name does not match.
     * It returns null if either no name or issuer ID number (IIN) was provided
     * in the request or if MaxMind does not have a name associated with the
     * IIN.
     */
    @JsonIgnore
    public final Boolean matchesProvidedName() {
        return matchesProvidedName;
    }

    /**
     * @return The phone number of the bank which issued the credit card. In
     * some cases the phone number we return may be out of date.
     */
    public final String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return This returns true if the phone number matches the number provided
     * in the request for the card issuer. It returns false if the number does
     * not match. It returns null if either no phone number or issuer ID number
     * (IIN) was provided in the request or if MaxMind does not have a phone
     * number associated with the IIN.
     */
    public final Boolean matchesProvidedPhoneNumber() {
        return matchesProvidedPhoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Issuer{");
        sb.append("name='").append(this.name).append('\'');
        sb.append(", matchesProvidedName=").append(this.matchesProvidedName);
        sb.append(", phoneNumber='").append(this.phoneNumber).append('\'');
        sb.append(", matchesProvidedPhoneNumber=").append(this.matchesProvidedPhoneNumber);
        sb.append('}');
        return sb.toString();
    }
}
