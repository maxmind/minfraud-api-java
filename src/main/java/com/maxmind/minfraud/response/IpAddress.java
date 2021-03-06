package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.model.InsightsResponse;
import com.maxmind.geoip2.record.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains minFraud response data related to the IP location
 */
public final class IpAddress extends InsightsResponse implements IpAddressInterface {
    private final GeoIp2Country country;
    private final GeoIp2Location location;
    private final Double risk;
    private final List<IpRiskReason> riskReasons;

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
            @JsonProperty("risk_reasons") List<IpRiskReason> riskReasons,
            @JsonProperty("subdivisions") List<Subdivision> subdivisions,
            @JsonProperty("traits") Traits traits
    ) {
        super(city, continent, country, location, maxmind, postal, registeredCountry, representedCountry, subdivisions, traits);
        this.country = country == null ? new GeoIp2Country() : country;
        this.location = location == null ? new GeoIp2Location() : location;
        this.risk = risk;
        this.riskReasons = Collections.unmodifiableList(riskReasons == null ? new ArrayList<>() : riskReasons);
    }

    /**
     * @deprecated This constructor only exists for backward compatibility
     * and will be removed in the next major release.
     *
     * @param city The city information.
     * @param continent The continent information.
     * @param country The country information.
     * @param location The location information.
     * @param maxmind MaxMind-specific information.
     * @param postal The postal information.
     * @param registeredCountry The information about the country where the IP was registered.
     * @param representedCountry The represented country, e.g., for military bases in other countries.
     * @param risk The IP risk.
     * @param subdivisions The list of subdivisions.
     * @param traits Information about various other IP traits.
     */
    public IpAddress(
            City city,
            Continent continent,
            GeoIp2Country country,
            GeoIp2Location location,
            MaxMind maxmind,
            Postal postal,
            Country registeredCountry,
            RepresentedCountry representedCountry,
            Double risk,
            List<Subdivision> subdivisions,
            Traits traits
    ) {
        this(city, continent, country, location, maxmind, postal, registeredCountry, representedCountry,
                risk, null, subdivisions, traits);
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

    /**
     * @return An unmodifiable list containing risk reason objects that identify
     * the reasons why the IP address received the associated risk. This will
     * be an empty list if there are no reasons.
     */
    @JsonProperty("risk_reasons")
    public final List<IpRiskReason> getRiskReasons() {
        return riskReasons;
    }
}
