package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Location;

/**
 * This class contains minFraud response data related to the GeoIP2 Insights
 * location.
 */
public final class GeoIp2Location extends Location {
    @JsonProperty("local_time")
    private String localTime;

    /**
     * @return The date and time of the transaction in the time zone associated
     * with the IP address. The value is formated according to RFC 3339. For
     * instance, the local time in Boston might be returned as
     * "2015-04-27T19:17:24-04:00".
     */
    public final String getLocalTime() {
        return this.localTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GeoIp2Location{");
        sb.append("localTime='").append(this.localTime).append('\'');
        sb.append(", super:").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
