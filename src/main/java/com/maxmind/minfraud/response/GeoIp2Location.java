package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Location;
import java.time.ZonedDateTime;

/**
 * This class contains minFraud response data related to the GeoIP2 Insights
 * location.
 */
public final class GeoIp2Location extends Location {
    private final String localTime;

    /**
     * Constructor for {@code EmailDomain}.
     *
     * @param accuracyRadius The approximate accuracy radius in kilometers.
     * @param averageIncome The average income in US dollars associated with the IP address.
     * @param latitude The approximate latitude of the location associated with the IP address.
     * @param localTime The date and time of the transaction in the time zone associated with
     * the IP address.
     * @param longitude The approximate longitude of the location associated with the IP address.
     * @param metroCode The metro code of the location if the location is in the US.
     * @param populationDensity The estimated population per square kilometer associated with
     * the IP address.
     * @param timeZone The time zone associated with the location.
     */
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
        super(accuracyRadius, averageIncome, latitude, longitude, metroCode, populationDensity,
            timeZone);
        this.localTime = localTime;
    }

    /**
     * Constructor for {@code GeoIp2Location}
     */
    public GeoIp2Location() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * @return The date and time of the transaction in the time zone associated
     * with the IP address. The value is formatted according to RFC 3339. For
     * instance, the local time in Boston might be returned as
     * "2015-04-27T19:17:24-04:00".
     */
    @JsonProperty("local_time")
    public String getLocalTime() {
        return this.localTime;
    }

    /**
     * @return The date and time of the transaction in the time zone associated
     * with the IP address as a {@code ZonedDateTime}.
     */
    @JsonIgnore
    public ZonedDateTime getLocalDateTime() {
        if (localTime == null) {
            return null;
        }
        return ZonedDateTime.parse(localTime);
    }
}
