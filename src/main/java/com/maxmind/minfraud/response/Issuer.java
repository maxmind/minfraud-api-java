package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class contains minFraud response data related to the credit card issuer.
 */
public final class Issuer extends AbstractModel {
    private final String name;
    private final Boolean matchesProvidedName;
    private final String phoneNumber;
    private final Boolean matchesProvidedPhoneNumber;

    public Issuer(
            @JsonProperty("matches_provided_name") Boolean matchesProvidedName,
            @JsonProperty("matches_provided_phone_number") Boolean matchesProvidedPhoneNumber,
            @JsonProperty("name") String name,
            @JsonProperty("phone_number") String phoneNumber
    ) {
        this.matchesProvidedName = matchesProvidedName;
        this.matchesProvidedPhoneNumber = matchesProvidedPhoneNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Issuer() {
        this(null, null, null, null);
    }

    /**
     * @return The name of the bank which issued the credit card.
     */
    public String getName() {
        return name;
    }

    /**
     * @return This returns true if the name matches the name provided in the
     * request for the card issuer. It returns false if the name does not match.
     * It returns null if either no name or issuer ID number (IIN) was provided
     * in the request or if MaxMind does not have a name associated with the
     * IIN.
     */
    @JsonProperty("matches_provided_name")
    public Boolean matchesProvidedName() {
        return matchesProvidedName;
    }

    /**
     * @return The phone number of the bank which issued the credit card. In
     * some cases the phone number we return may be out of date.
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return This returns true if the phone number matches the number provided
     * in the request for the card issuer. It returns false if the number does
     * not match. It returns null if either no phone number or issuer ID number
     * (IIN) was provided in the request or if MaxMind does not have a phone
     * number associated with the IIN.
     */
    @JsonProperty("matches_provided_phone_number")
    public Boolean matchesProvidedPhoneNumber() {
        return matchesProvidedPhoneNumber;
    }
}
