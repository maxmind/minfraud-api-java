package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the billing address.
 */
public final class BillingAddress extends AbstractAddress {
    /**
     * constructor for {@code BillingAddress}.
     * @param distanceToIpLocation The distance in kilometers from the billing
     * address to the IP location.
     * @param isInIpCountry This is true if the billing address is in the IP
     * country. 
     * @param isPostalInCity This is true if the billing postal code is in the
     * city for the IP location.
     * @param latitude The latitude associated with the IP address.
     * @param longitude The longitude associated with the IP address.
     */
    public BillingAddress(
        @JsonProperty("distance_to_ip_location") Integer distanceToIpLocation,
        @JsonProperty("is_in_ip_country") Boolean isInIpCountry,
        @JsonProperty("is_postal_in_city") Boolean isPostalInCity,
        @JsonProperty("latitude") Double latitude,
        @JsonProperty("longitude") Double longitude
    ) {
        super(distanceToIpLocation, isInIpCountry, isPostalInCity, latitude, longitude);
    }

    /** 
    * constructor for {@code BillingAddress}.
    */
    public BillingAddress() {
        this(null, null, null, null, null);
    }
}
