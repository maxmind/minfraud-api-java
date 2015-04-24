package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Location;

public class GeoIp2Location extends Location {
    @JsonProperty("local_time")
    private String localTime;

    // XXX - possibly switch to Joda DateTime. We can't
    // return a j.u.Date object as that doesn't have the tz info
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
