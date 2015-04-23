package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.model.InsightsResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Location;

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
    public GeoIp2Country getCountry() {
        return this.country;
    }

    /**
     * @return Location record for the requested IP address.
     */
    public GeoIp2Location getLocation() {
        return this.location;
    }

}
