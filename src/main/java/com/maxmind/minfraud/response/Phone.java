package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class contains minFraud response data related to the phone number.
 *
 * @param country         The two-character ISO 3166-1 country code for the country associated
 *                        with the phone number.
 * @param isVoip          Whether the number is VoIP.
 * @param matchesPostal   Whether the phone number matches the postal code.
 * @param networkOperator The name of the original network operator associated with the phone
 *                        number. This field does not reflect phone numbers that have been ported
 *                        from the original operator to another, nor does it identify mobile
 *                        virtual network operators.
 * @param numberType      One of the following values: {@code fixed} or {@code mobile}.
 *                        Additional values may be added in the future.
 */
public record Phone(
    @JsonProperty("country")
    String country,

    @JsonProperty("is_voip")
    Boolean isVoip,

    @JsonProperty("matches_postal")
    Boolean matchesPostal,

    @JsonProperty("network_operator")
    String networkOperator,

    @JsonProperty("number_type")
    String numberType
) implements JsonSerializable {

    /**
     * Constructs an instance of {@code Phone} with no data.
     */
    public Phone() {
        this(null, null, null, null, null);
    }

    /**
     * @return The two-character ISO 3166-1 country code for the country associated with the phone
     *     number.
     * @deprecated Use {@link #country()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("country")
    public String getCountry() {
        return country();
    }

    /**
     * @return The name of the original network operator associated with the phone number. This
     *     field does not reflect phone numbers that have been ported from the original operator to
     *     another, nor does it identify mobile virtual network operators.
     * @deprecated Use {@link #networkOperator()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("network_operator")
    public String getNetworkOperator() {
        return networkOperator();
    }

    /**
     * @return One of the following values: {@code fixed} or {@code mobile}. Additional values may
     *     be added in the future.
     * @deprecated Use {@link #numberType()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("number_type")
    public String getNumberType() {
        return numberType();
    }
}
