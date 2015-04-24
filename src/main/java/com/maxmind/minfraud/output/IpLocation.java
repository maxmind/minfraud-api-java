package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.model.InsightsResponse;

public class IpLocation extends InsightsResponse {
    @JsonProperty("country")
    private final GeoIp2Country country = new GeoIp2Country();
    @JsonProperty("location")
    private final GeoIp2Location location = new GeoIp2Location();

    /**
     * @return Country record for the requested IP address. This object
     * represents the country where MaxMind believes the end user is
     * located.
     */
    public final GeoIp2Country getCountry() {
        return country;
    }

    /**
     * @return Location record for the requested IP address.
     */
    public final GeoIp2Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IpLocation{");
        sb.append("country=").append(this.country);
        sb.append(", location=").append(this.location);
        sb.append('}');
        return sb.toString();
    }
}
