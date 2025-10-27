package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class contains minFraud response data related to the shipping address.
 *
 * @param distanceToBillingAddress The distance in kilometers from the shipping address to billing
 *                                 address.
 * @param distanceToIpLocation     The distance in kilometers from the address to the IP location.
 *                                 This will be null if there is no value in the response.
 * @param isHighRisk               This returns true if the shipping address is an address
 *                                 associated with fraudulent transactions. It returns false when
 *                                 the address is not associated with increased risk. If the address
 *                                 could not be parsed or was not provided, null is returned.
 * @param isInIpCountry            This returns true if the address is in the IP country. It is
 *                                 false when the address is not in the IP country. If the address
 *                                 could not be parsed or was not provided or the IP address could
 *                                 not be geolocated, then null will be returned.
 * @param isPostalInCity           This will return true if the postal code provided with the
 *                                 address is in the city for the address. It will return false when
 *                                 the postal code is not in the city. If the address was not
 *                                 provided or could not be parsed, null will be returned.
 * @param latitude                 The latitude associated with the address. This will be null if
 *                                 there is no value in the response.
 * @param longitude                The longitude associated with the address. This will be null if
 *                                 there is no value in the response.
 */
public record ShippingAddress(
    @JsonProperty("distance_to_billing_address")
    Integer distanceToBillingAddress,

    @JsonProperty("distance_to_ip_location")
    Integer distanceToIpLocation,

    @JsonProperty("is_high_risk")
    Boolean isHighRisk,

    @JsonProperty("is_in_ip_country")
    Boolean isInIpCountry,

    @JsonProperty("is_postal_in_city")
    Boolean isPostalInCity,

    @JsonProperty("latitude")
    Double latitude,

    @JsonProperty("longitude")
    Double longitude
) implements JsonSerializable {

    /**
     * Constructs an instance of {@code ShippingAddress} with no data.
     */
    public ShippingAddress() {
        this(null, null, null, null, null, null, null);
    }

    /**
     * @return The distance in kilometers from the shipping address to billing address.
     * @deprecated Use {@link #distanceToBillingAddress()} instead. This method will be removed
     *     in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("distance_to_billing_address")
    public Integer getDistanceToBillingAddress() {
        return distanceToBillingAddress();
    }

    /**
     * @return The latitude associated with the address.
     * @deprecated Use {@link #latitude()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public Double getLatitude() {
        return latitude();
    }

    /**
     * @return The longitude associated with the address.
     * @deprecated Use {@link #longitude()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public Double getLongitude() {
        return longitude();
    }

    /**
     * @return The distance in kilometers from the address to the IP location.
     * @deprecated Use {@link #distanceToIpLocation()} instead. This method will be removed in
     *     5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    public Integer getDistanceToIpLocation() {
        return distanceToIpLocation();
    }
}

