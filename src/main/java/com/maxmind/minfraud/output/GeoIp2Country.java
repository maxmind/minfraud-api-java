package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Country;

public class GeoIp2Country extends Country
{
    @JsonProperty("is_high_risk")
    private boolean isHighRisk;

    @JsonIgnore
    public boolean isHighRisk() {
        return isHighRisk;
    }
}
