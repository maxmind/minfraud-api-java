package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the billing address.
 */
public final class BillingAddress extends AbstractAddress {
    public BillingAddress(
            @JsonProperty("distance_to_ip_location") Integer distanceToIpLocation,
            @JsonProperty("is_in_ip_country") Boolean isInIpCountry,
            @JsonProperty("is_postal_in_city") Boolean isPostalInCity,
            @JsonProperty("latitude") Double latitude,
            @JsonProperty("longitude") Double longitude
    ) {
        super(distanceToIpLocation, isInIpCountry, isPostalInCity, latitude, longitude);
    }

    public BillingAddress() {
        this(null, null, null, null, null);
    }
}
