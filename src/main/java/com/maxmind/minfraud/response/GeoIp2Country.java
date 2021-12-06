package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.geoip2.record.Country;

import java.util.List;
import java.util.Map;

/**
 * This class contains minFraud response data related to the GeoIP2 Insights
 * country.
 */
public final class GeoIp2Country extends Country {
    private final boolean isHighRisk;

    /**
     * @param locales    The locales.
     * @param confidence The confidence.
     * @param geoNameId  The GeoName ID.
     * @param isHighRisk Whether it is a high risk country.
     * @param isoCode    The ISO code.
     * @param names      The names.
     * @deprecated This constructor only exists for backward compatibility
     * and will be removed in the next major release.
     */
    public GeoIp2Country(
            List<String> locales,
            Integer confidence,
            Integer geoNameId,
            boolean isHighRisk,
            String isoCode,
            Map<String, String> names
    ) {
        this(locales, confidence, geoNameId, isHighRisk, false, isoCode, names);
    }

    public GeoIp2Country(
            @JacksonInject("locales") List<String> locales,
            @JsonProperty("confidence") Integer confidence,
            @JsonProperty("geoname_id") Integer geoNameId,
            @JsonProperty("is_high_risk") boolean isHighRisk,
            @JsonProperty("is_in_european_union") boolean isInEuropeanUnion,
            @JsonProperty("iso_code") String isoCode,
            @JsonProperty("names") Map<String, String> names
    ) {
        super(locales, confidence, geoNameId, isInEuropeanUnion, isoCode,
                names);
        this.isHighRisk = isHighRisk;
    }

    public GeoIp2Country() {
        this(null, null, null, false, false, null, null);
    }

    /**
     * @return This value is true if the IP country is high risk.
     * @deprecated Deprecated effective August 29, 2019.
     */
    @JsonProperty("is_high_risk")
    @Deprecated
    public boolean isHighRisk() {
        return this.isHighRisk;
    }
}
