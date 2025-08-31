package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class contains minFraud response data related to the phone number.
 */
public final class Phone extends AbstractModel {
    private final String country;
    private final Boolean isVoip;
    private final Boolean matchesPostal;
    private final String networkOperator;
    private final String numberType;

    /**
     * @param country         The ISO 3166-2 country code for the phone number.
     * @param isVoip          Whether the number is VoIP.
     * @param matchesPostal   Whether the phone number matches the postal code.
     * @param networkOperator The network operator associated with the phone number.
     * @param numberType      The type of the phone number.
     */
    public Phone(
        @JsonProperty("country") String country,
        @JsonProperty("is_voip") Boolean isVoip,
        @JsonProperty("matches_postal") Boolean matchesPostal,
        @JsonProperty("network_operator") String networkOperator,
        @JsonProperty("number_type") String numberType
    ) {
        this.country = country;
        this.isVoip = isVoip;
        this.matchesPostal = matchesPostal;
        this.networkOperator = networkOperator;
        this.numberType = numberType;
    }

    /**
     * Constructor for {@code Phone}.
     */
    public Phone() {
        this(null, null, null, null, null);
    }

    /**
     * @return The two-character ISO 3166-1 country code for the country associated with the  phone
     *     number.
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * @return Whether the  phone number is a Voice over Internet Protocol (VoIP) number allocated
     *     by a regulator. The value will be {@code null} if a valid phone number was not provided
     *     or if we do not have data for the phone number.
     */
    @JsonProperty("is_voip")
    public Boolean isVoip() {
        return isVoip;
    }

    /**
     * @return This is {@code true} if the phone number's prefix is commonly associated with the
     *     postal code. It is {@code false} if the prefix is not associated with the postal code.
     *     It is non-{@code null} only when the phone number is in the US, the number prefix is
     *     in our database, and the postal code and country are provided in the request.
     */
    @JsonProperty("matches_postal")
    public Boolean matchesPostal() {
        return matchesPostal;
    }

    /**
     * @return The name of the original network operator associated with the phone number. This
     *     field does not reflect phone numbers that have been ported from the original operator to
     *     another, nor does it identify mobile virtual network operators.
     */
    @JsonProperty("network_operator")
    public String getNetworkOperator() {
        return networkOperator;
    }

    /**
     * @return One of the following values: {@code fixed} or {@code mobile}. Additional values may
     *     be added in the future.
     */
    @JsonProperty("number_type")
    public String getNumberType() {
        return numberType;
    }
}
