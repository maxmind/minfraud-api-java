package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.model.InsightsResponse;
import com.maxmind.geoip2.record.*;

import java.util.List;

/**
 * This class contains minFraud response data related to the IP location
 */
public final class IpAddress extends InsightsResponse implements IpAddressInterface {
    private final GeoIp2Country country;
    private final GeoIp2Location location;
    private final Double risk;

    public IpAddress(
            @JsonProperty("city") City city,
            @JsonProperty("continent") Continent continent,
            @JsonProperty("country") GeoIp2Country country,
            @JsonProperty("location") GeoIp2Location location,
            @JsonProperty("maxmind") MaxMind maxmind,
            @JsonProperty("postal") Postal postal,
            @JsonProperty("registered_country") Country registeredCountry,
            @JsonProperty("represented_country") RepresentedCountry representedCountry,
            @JsonProperty("risk") Double risk,
            @JsonProperty("subdivisions") List<Subdivision> subdivisions,
            @JsonProperty("traits") Traits traits
    ) {
        super(city, continent, country, location, maxmind, postal, registeredCountry, representedCountry, subdivisions, traits);
        this.country = country == null ? new GeoIp2Country() : country;
        this.location = location == null ? new GeoIp2Location() : location;
        this.risk = risk;
    }

    public IpAddress() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * @return Country record for the requested IP address. This object
     * represents the country where MaxMind believes the end user is
     * located.
     */
    @Override
    public GeoIp2Country getCountry() {
        return country;
    }

    /**
     * @return Location record for the requested IP address.
     */
    @Override
    public GeoIp2Location getLocation() {
        return location;
    }


    /**
     * @return The risk associated with the IP address. The value ranges from
     * 0.01 to 99. A higher score indicates a higher risk.
     */
    public Double getRisk() {
        return risk;
    }
}
