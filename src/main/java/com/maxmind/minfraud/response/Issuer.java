package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class contains minFraud response data related to the credit card issuer.
 *
 * @param matchesProvidedName        This is true if the name matches the name provided.
 * @param matchesProvidedPhoneNumber This is true if the phone number matches the one provided.
 * @param name                       The name of the bank which issued the credit card.
 * @param phoneNumber                The phone number of the bank which issued the credit card. In
 *                                   some cases the phone number we return may be out of date.
 */
public record Issuer(
    @JsonProperty("matches_provided_name")
    Boolean matchesProvidedName,

    @JsonProperty("matches_provided_phone_number")
    Boolean matchesProvidedPhoneNumber,

    @JsonProperty("name")
    String name,

    @JsonProperty("phone_number")
    String phoneNumber
) implements JsonSerializable {

    /**
     * Constructs an instance of {@code Issuer} with no data.
     */
    public Issuer() {
        this(null, null, null, null);
    }

    /**
     * @return The name of the bank which issued the credit card.
     * @deprecated Use {@link #name()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("name")
    public String getName() {
        return name();
    }

    /**
     * @return The phone number of the bank which issued the credit card. In some cases the phone
     *     number we return may be out of date.
     * @deprecated Use {@link #phoneNumber()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber();
    }
}
