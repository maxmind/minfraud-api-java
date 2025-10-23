package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.model.InsightsResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Continent;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.RepresentedCountry;
import com.maxmind.geoip2.record.Subdivision;
import com.maxmind.geoip2.record.Traits;
import java.util.Collections;
import java.util.List;

/**
 * This class contains minFraud response data related to the IP location
 */
public final class IpAddress implements IpAddressInterface {
    private final InsightsResponse insightsResponse;
    private final GeoIp2Location location;
    private final Double risk;
    private final List<IpRiskReason> riskReasons;

    /**
     * Constructor for {@code IpAddress}
     *
     * @param city               The city information.
     * @param continent          The continent information.
     * @param country            The country information.
     * @param location           The location information.
     * @param postal             The postal information.
     * @param registeredCountry  The information about the country where the IP was registered.
     * @param representedCountry The represented country, e.g., for military bases in other
     *                           countries.
     * @param risk               The IP risk.
     * @param riskReasons        The reasons for the IP risk.
     * @param subdivisions       The list of subdivisions.
     * @param traits             Information about various other IP traits.
     */
    public IpAddress(
        @JsonProperty("city") City city,
        @JsonProperty("continent") Continent continent,
        @JsonProperty("country") Country country,
        @JsonProperty("location") GeoIp2Location location,
        @JsonProperty("postal") Postal postal,
        @JsonProperty("registered_country") Country registeredCountry,
        @JsonProperty("represented_country") RepresentedCountry representedCountry,
        @JsonProperty("risk") Double risk,
        @JsonProperty("risk_reasons") List<IpRiskReason> riskReasons,
        @JsonProperty("subdivisions") List<Subdivision> subdivisions,
        @JsonProperty("traits") Traits traits
    ) {
        // Store all the GeoIP2 data directly without using InsightsResponse for storage
        this.insightsResponse = new InsightsResponse(city, continent, country, null, null,
            postal, registeredCountry, representedCountry, subdivisions, traits);
        this.location = location == null ? new GeoIp2Location() : location;
        this.risk = risk;
        this.riskReasons =
            Collections.unmodifiableList(riskReasons == null ? List.of() : riskReasons);
    }

    /**
     * Constructor for {@code IpAddress}.
     */
    public IpAddress() {
        this(null, null, null, null, null, null,
            null, null, null, null, null);
    }

    /**
     * @return Location record for the requested IP address.
     */
    @JsonProperty("location")
    public GeoIp2Location getLocation() {
        return location;
    }

    /**
     * @return City record for the requested IP address.
     */
    @JsonProperty("city")
    public City getCity() {
        return insightsResponse.getCity();
    }

    /**
     * @return Continent record for the requested IP address.
     */
    @JsonProperty("continent")
    public Continent getContinent() {
        return insightsResponse.getContinent();
    }

    /**
     * @return Country record for the requested IP address.
     */
    @JsonProperty("country")
    public Country getCountry() {
        return insightsResponse.getCountry();
    }

    /**
     * @return Postal record for the requested IP address.
     */
    @JsonProperty("postal")
    public Postal getPostal() {
        return insightsResponse.getPostal();
    }

    /**
     * @return Registered country record for the requested IP address.
     */
    @JsonProperty("registered_country")
    public Country getRegisteredCountry() {
        return insightsResponse.getRegisteredCountry();
    }

    /**
     * @return Represented country record for the requested IP address.
     */
    @JsonProperty("represented_country")
    public RepresentedCountry getRepresentedCountry() {
        return insightsResponse.getRepresentedCountry();
    }

    /**
     * @return List of subdivision records for the requested IP address.
     */
    @JsonProperty("subdivisions")
    public List<Subdivision> getSubdivisions() {
        return insightsResponse.getSubdivisions();
    }

    /**
     * @return Traits record for the requested IP address.
     */
    @JsonProperty("traits")
    public Traits getTraits() {
        return insightsResponse.getTraits();
    }

    /**
     * @return The risk associated with the IP address. The value ranges from 0.01 to 99. A higher
     *     score indicates a higher risk.
     */
    public Double getRisk() {
        return risk;
    }

    /**
     * @return An unmodifiable list containing risk reason objects that identify the reasons why the
     *     IP address received the associated risk. This will be an empty list if there are no
     *     reasons.
     */
    @JsonProperty("risk_reasons")
    public List<IpRiskReason> getRiskReasons() {
        return riskReasons;
    }
}
