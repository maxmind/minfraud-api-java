package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Country;

/**
 * This class contains minFraud response data related to the GeoIP2 Insights
 * country.
 */
public final class GeoIp2Country extends Country {
    @JsonProperty("is_high_risk")
    private boolean isHighRisk;

    /**
     * @return This value is true if the IP country is high risk.
     */
    @JsonIgnore
    public final boolean isHighRisk() {
        return this.isHighRisk;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GeoIp2Country{");
        sb.append("isHighRisk=").append(this.isHighRisk);
        sb.append(", super:").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
