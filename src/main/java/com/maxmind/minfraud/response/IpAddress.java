package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.model.InsightsResponse;

/**
 * This class contains minFraud response data related to the IP location
 */
public final class IpAddress extends InsightsResponse {
    @JsonProperty("country")
    private final GeoIp2Country country = new GeoIp2Country();
    @JsonProperty("location")
    private final GeoIp2Location location = new GeoIp2Location();
    @JsonProperty("risk")
    protected Double risk;

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


    /**
     * @return The risk associated with the IP address. The value ranges from
     * 0.01 to 99. A higher score indicates a higher risk.
     */
    public Double getRisk() {
        return risk;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IpAddress{");
        sb.append("risk=").append(this.risk);
        sb.append(", country=").append(this.country);
        sb.append(", location=").append(this.location);
        sb.append(", super:").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
