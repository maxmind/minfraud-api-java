package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.time.ZonedDateTime;

/**
 * This class contains minFraud response data related to the GeoIP2 Insights location.
 *
 * @param accuracyRadius    The approximate accuracy radius in kilometers around the latitude and
 *                          longitude for the geographical entity (country, subdivision, city or
 *                          postal code) associated with the IP address.
 * @param averageIncome     The average income in US dollars associated with the requested IP
 *                          address.
 * @param latitude          The approximate latitude of the location associated with the IP
 *                          address.
 * @param localTime         The date and time of the transaction in the time zone associated with
 *                          the IP address. The value is formatted according to RFC 3339. For
 *                          instance, the local time in Boston might be returned as
 *                          "2015-04-27T19:17:24-04:00".
 * @param longitude         The approximate longitude of the location associated with the IP
 *                          address.
 * @param metroCode         Deprecated. The no-longer-maintained code for targeting advertisements
 *                          in Google, if the location is in the US.
 * @param populationDensity The estimated population per square kilometer associated with the IP
 *                          address.
 * @param timeZone          The time zone associated with location, as specified by the IANA Time
 *                          Zone Database.
 */
public record GeoIp2Location(
    @JsonProperty("accuracy_radius")
    Integer accuracyRadius,

    @JsonProperty("average_income")
    Integer averageIncome,

    @JsonProperty("latitude")
    Double latitude,

    @JsonProperty("local_time")
    String localTime,

    @JsonProperty("longitude")
    Double longitude,

    @JsonProperty("metro_code")
    Integer metroCode,

    @JsonProperty("population_density")
    Integer populationDensity,

    @JsonProperty("time_zone")
    String timeZone
) implements JsonSerializable {

    /**
     * Constructs an instance of {@code GeoIp2Location} with no data.
     */
    public GeoIp2Location() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * @return The approximate accuracy radius in kilometers around the latitude and longitude
     *     for the geographical entity (country, subdivision, city or postal code) associated
     *     with the IP address.
     * @deprecated Use {@link #accuracyRadius()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("accuracy_radius")
    public Integer getAccuracyRadius() {
        return accuracyRadius();
    }

    /**
     * @return The average income in US dollars associated with the requested IP address.
     * @deprecated Use {@link #averageIncome()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("average_income")
    public Integer getAverageIncome() {
        return averageIncome();
    }

    /**
     * @return The approximate latitude of the location associated with the IP address.
     * @deprecated Use {@link #latitude()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude();
    }

    /**
     * @return The approximate longitude of the location associated with the IP address.
     * @deprecated Use {@link #longitude()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude();
    }

    /**
     * @return The no-longer-maintained code for targeting advertisements in Google.
     * @deprecated Use {@link #metroCode()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("metro_code")
    public Integer getMetroCode() {
        return metroCode();
    }

    /**
     * @return The estimated population per square kilometer associated with the IP address.
     * @deprecated Use {@link #populationDensity()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("population_density")
    public Integer getPopulationDensity() {
        return populationDensity();
    }

    /**
     * @return The time zone associated with location, as specified by the IANA Time Zone Database.
     * @deprecated Use {@link #timeZone()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("time_zone")
    public String getTimeZone() {
        return timeZone();
    }

    /**
     * @return The date and time of the transaction in the time zone associated with the IP address.
     *     The value is formatted according to RFC 3339. For instance, the local time in Boston
     *     might be returned as "2015-04-27T19:17:24-04:00".
     * @deprecated Use {@link #localTime()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("local_time")
    public String getLocalTime() {
        return localTime();
    }

    /**
     * @return The date and time of the transaction in the time zone associated with the IP address
     *     as a {@code ZonedDateTime}.
     */
    @JsonIgnore
    public ZonedDateTime getLocalDateTime() {
        if (localTime == null) {
            return null;
        }
        return ZonedDateTime.parse(localTime);
    }
}
