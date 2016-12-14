package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

public abstract class AbstractAddress extends AbstractModel {
    private final Boolean isPostalInCity;
    private final Double latitude;
    private final Double longitude;
    private final Integer distanceToIpLocation;
    private final Boolean isInIpCountry;

    protected AbstractAddress(Integer distanceToIpLocation, Boolean isInIpCountry, Boolean isPostalInCity, Double latitude, Double longitude) {
        this.distanceToIpLocation = distanceToIpLocation;
        this.isInIpCountry = isInIpCountry;
        this.isPostalInCity = isPostalInCity;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return This returns true if the address is in the IP country. It is
     * false when the address is not in the IP country. If the address
     * could not be parsed or was not provided or the IP address could not
     * be geo-located, then null will be returned.
     */
    @JsonProperty("is_in_ip_country")
    public final Boolean isInIpCountry() {
        return isInIpCountry;
    }

    /**
     * @return The latitude associated with the address. This will be null if
     * there is no value in the response.
     */
    public final Double getLatitude() {
        return latitude;
    }

    /**
     * @return The longitude associated with the address. This will be null if
     * there is no value in the response.
     */
    public final Double getLongitude() {
        return longitude;
    }

    /**
     * @return The distance in kilometers from the address to the IP location
     * in kilometers. This will be null if there is no value in the
     * response.
     */
    @JsonProperty("distance_to_ip_location")
    public final Integer getDistanceToIpLocation() {
        return distanceToIpLocation;
    }

    /**
     * @return This will return true if the postal code provided with the
     * address is in the city for the address. It will return false when the
     * postal code is not in the city. If the address was not provided
     * or could not be parsed, null will be returned.
     */
    @JsonProperty("is_postal_in_city")
    public final Boolean isPostalInCity() {
        return isPostalInCity;
    }
}
