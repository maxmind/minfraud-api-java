package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Anonymizer;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Continent;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.RepresentedCountry;
import com.maxmind.geoip2.record.Subdivision;
import com.maxmind.geoip2.record.Traits;
import com.maxmind.minfraud.JsonSerializable;
import java.util.List;

/**
 * This class contains minFraud response data related to the IP location.
 *
 * @param anonymizer         Anonymizer record for the requested IP address. This contains
 *                           information about whether the IP address is an anonymous network.
 * @param city               City record for the requested IP address.
 * @param continent          Continent record for the requested IP address.
 * @param country            Country record for the requested IP address.
 * @param location           Location record for the requested IP address.
 * @param postal             Postal record for the requested IP address.
 * @param registeredCountry  Registered country record for the requested IP address.
 * @param representedCountry Represented country record for the requested IP address.
 * @param risk               The risk associated with the IP address.
 * @param riskReasons        An unmodifiable list containing risk reason objects that identify the
 *                           reasons why the IP address received the associated risk. This will be
 *                           an empty list if there are no reasons.
 * @param subdivisions       List of subdivision records for the requested IP address.
 * @param traits             Traits record for the requested IP address.
 */
public record IpAddress(
    @JsonProperty("anonymizer")
    Anonymizer anonymizer,
    @JsonProperty("city")
    City city,

    @JsonProperty("continent")
    Continent continent,

    @JsonProperty("country")
    Country country,

    @JsonProperty("location")
    GeoIp2Location location,

    @JsonProperty("postal")
    Postal postal,

    @JsonProperty("registered_country")
    Country registeredCountry,

    @JsonProperty("represented_country")
    RepresentedCountry representedCountry,

    @JsonProperty("risk")
    Double risk,

    @JsonProperty("risk_reasons")
    List<IpRiskReason> riskReasons,

    @JsonProperty("subdivisions")
    List<Subdivision> subdivisions,

    @JsonProperty("traits")
    Traits traits
) implements IpAddressInterface, JsonSerializable {

    /**
     * Compact canonical constructor that sets defaults for null values.
     */
    public IpAddress {
        anonymizer = anonymizer != null ? anonymizer : new Anonymizer();
        location = location != null ? location : new GeoIp2Location();
        riskReasons = riskReasons != null ? List.copyOf(riskReasons) : List.of();
        subdivisions = subdivisions != null ? List.copyOf(subdivisions) : List.of();
    }

    /**
     * Constructs an instance of {@code IpAddress} with no data.
     */
    public IpAddress() {
        this(null, null, null, null, null, null,
            null, null, null, null, null, null);
    }

    /**
     * Constructs an instance of {@code IpAddress}.
     *
     * @param city               City record for the requested IP address.
     * @param continent          Continent record for the requested IP address.
     * @param country            Country record for the requested IP address.
     * @param location           Location record for the requested IP address.
     * @param postal             Postal record for the requested IP address.
     * @param registeredCountry  Registered country record for the requested IP address.
     * @param representedCountry Represented country record for the requested IP address.
     * @param risk               The risk associated with the IP address.
     * @param riskReasons        List of risk reason objects.
     * @param subdivisions       List of subdivision records for the requested IP address.
     * @param traits             Traits record for the requested IP address.
     * @deprecated Use the canonical constructor instead. This constructor will be removed in 5.0.0.
     */
    @Deprecated(since = "4.1.0", forRemoval = true)
    public IpAddress(
        City city,
        Continent continent,
        Country country,
        GeoIp2Location location,
        Postal postal,
        Country registeredCountry,
        RepresentedCountry representedCountry,
        Double risk,
        List<IpRiskReason> riskReasons,
        List<Subdivision> subdivisions,
        Traits traits
    ) {
        this(null, city, continent, country, location, postal,
            registeredCountry, representedCountry, risk, riskReasons,
            subdivisions, traits);
    }

    /**
     * @return Location record for the requested IP address.
     * @deprecated Use {@link #location()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("location")
    public GeoIp2Location getLocation() {
        return location();
    }

    /**
     * @return City record for the requested IP address.
     * @deprecated Use {@link #city()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("city")
    public City getCity() {
        return city();
    }

    /**
     * @return Continent record for the requested IP address.
     * @deprecated Use {@link #continent()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("continent")
    public Continent getContinent() {
        return continent();
    }

    /**
     * @return Country record for the requested IP address.
     * @deprecated Use {@link #country()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("country")
    public Country getCountry() {
        return country();
    }

    /**
     * @return Postal record for the requested IP address.
     * @deprecated Use {@link #postal()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("postal")
    public Postal getPostal() {
        return postal();
    }

    /**
     * @return Registered country record for the requested IP address.
     * @deprecated Use {@link #registeredCountry()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("registered_country")
    public Country getRegisteredCountry() {
        return registeredCountry();
    }

    /**
     * @return Represented country record for the requested IP address.
     * @deprecated Use {@link #representedCountry()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("represented_country")
    public RepresentedCountry getRepresentedCountry() {
        return representedCountry();
    }

    /**
     * @return List of subdivision records for the requested IP address.
     * @deprecated Use {@link #subdivisions()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("subdivisions")
    public List<Subdivision> getSubdivisions() {
        return subdivisions();
    }

    /**
     * @return Traits record for the requested IP address.
     * @deprecated Use {@link #traits()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("traits")
    public Traits getTraits() {
        return traits();
    }

    /**
     * @return The risk associated with the IP address.
     * @deprecated Use {@link #risk()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("risk")
    public Double getRisk() {
        return risk();
    }

    /**
     * @return An unmodifiable list containing risk reason objects that identify the reasons why the
     *     IP address received the associated risk. This will be an empty list if there are no
     *     reasons.
     * @deprecated Use {@link #riskReasons()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("risk_reasons")
    public List<IpRiskReason> getRiskReasons() {
        return riskReasons();
    }
}
