package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

abstract class Address {
    @JsonProperty("is_postal_in_city")
    protected Boolean isPostalInCity;
    protected Double latitude;
    protected Double longitude;

    @JsonProperty("distance_to_ip_location")
    protected Integer distanceToIpLocation;

    @JsonProperty("is_in_ip_country")
    protected Boolean isInIpCountry;

    @JsonIgnore
    public Boolean isInIpCountry() {
        return this.isInIpCountry;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Integer getDistanceToIpLocation() {
        return this.distanceToIpLocation;
    }

    @JsonIgnore
    public Boolean isPostalInCity() {
        return this.isPostalInCity;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    @Override
    public String toString() {
        return "AbstractAddress{" +
                "isPostalInCity=" + isPostalInCity +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distanceToIpLocation=" + distanceToIpLocation +
                ", isInIpCountry=" + isInIpCountry +
                '}';
    }
}
