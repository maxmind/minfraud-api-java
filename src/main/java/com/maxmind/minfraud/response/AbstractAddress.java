package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

abstract class AbstractAddress {
    @JsonProperty("is_postal_in_city")
    protected Boolean isPostalInCity;
    protected Double latitude;
    protected Double longitude;

    @JsonProperty("distance_to_ip_location")
    protected Integer distanceToIpLocation;

    @JsonProperty("is_in_ip_country")
    protected Boolean isInIpCountry;

    /**
     * @return This returns true if the address is in the IP country. It is
     * false when the address is not in the IP country. If the address
     * could not be parsed or was not provided or the IP address could not
     * be geo-located, then null will be returned.
     */
    @JsonIgnore
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
    public final Integer getDistanceToIpLocation() {
        return distanceToIpLocation;
    }

    /**
     * @return This will return true if the postal code provided with the
     * address is in the city for the address. It will return false when the
     * postal code is not in the city. If the address could not be parsed or
     * was not provided, the null will be returned.
     */
    @JsonIgnore
    public final Boolean isPostalInCity() {
        return isPostalInCity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AbstractAddress{");
        sb.append("isPostalInCity=").append(this.isPostalInCity);
        sb.append(", latitude=").append(this.latitude);
        sb.append(", longitude=").append(this.longitude);
        sb.append(", distanceToIpLocation=").append(this.distanceToIpLocation);
        sb.append(", isInIpCountry=").append(this.isInIpCountry);
        sb.append(", inIpCountry=").append(this.isInIpCountry());
        sb.append(", postalInCity=").append(this.isPostalInCity());
        sb.append('}');
        return sb.toString();
    }
}
