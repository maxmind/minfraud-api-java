package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Location;

public class GeoIp2Location extends Location
{
    @JsonProperty("local_time")
    private String localTime;

    // XXX - possibly switch to Joda DateTime. We can't
    // return a j.u.Date object as that doesn't have the tz info
    public String getLocalTime() {
        return localTime;
    }
}
