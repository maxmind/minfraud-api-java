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

    @JsonIgnore
    public final Boolean isInIpCountry() {
        return isInIpCountry;
    }

    public final Double getLongitude() {
        return longitude;
    }

    public final Integer getDistanceToIpLocation() {
        return distanceToIpLocation;
    }

    @JsonIgnore
    public final Boolean isPostalInCity() {
        return isPostalInCity;
    }

    public final Double getLatitude() {
        return latitude;
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
