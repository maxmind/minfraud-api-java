package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class contains minFraud response data related to the phone number.
 */
public final class Phone extends AbstractModel {
    private final String country;
    private final Boolean isVoip;
    private final String networkOperator;
    private final String numberType;

    /**
     * @param country         The ISO 3166-2 country code for the phone number.
     * @param isVoip          Whether the number is VoIP.
     * @param networkOperator The network operator associated with the phone number.
     * @param numberType      The type of the phone number.
     */
    public Phone(
        @JsonProperty("country") String country,
        @JsonProperty("is_voip") Boolean isVoip,
        @JsonProperty("network_operator") String networkOperator,
        @JsonProperty("number_type") String numberType
    ) {
        this.country = country;
        this.isVoip = isVoip;
        this.networkOperator = networkOperator;
        this.numberType = numberType;
    }

    /**
     * Constructor for {@code Phone}.
     */
    public Phone() {
        this(null, null, null, null);
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
