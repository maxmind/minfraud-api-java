package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Location;

/**
 * This class contains minFraud response data related to the GeoIP2 Insights
 * location.
 */
public final class GeoIp2Location extends Location {
    private final String localTime;

    public GeoIp2Location(
            @JsonProperty("accuracy_radius") Integer accuracyRadius,
            @JsonProperty("average_income") Integer averageIncome,
            @JsonProperty("latitude") Double latitude,
            @JsonProperty("local_time") String localTime,
            @JsonProperty("longitude") Double longitude,
            @JsonProperty("metro_code") Integer metroCode,
            @JsonProperty("population_density") Integer populationDensity,
            @JsonProperty("time_zone") String timeZone
    ) {
        super(accuracyRadius, averageIncome, latitude, longitude, metroCode, populationDensity, timeZone);
        this.localTime = localTime;
    }

    public GeoIp2Location() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * @return The date and time of the transaction in the time zone associated
     * with the IP address. The value is formated according to RFC 3339. For
     * instance, the local time in Boston might be returned as
     * "2015-04-27T19:17:24-04:00".
     */
    @JsonProperty("local_time")
    public String getLocalTime() {
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
