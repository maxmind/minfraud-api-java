package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class contains minFraud response data related to the email address.
 */
public final class Email extends AbstractModel {
    private final Boolean isFree;
    private final Boolean isHighRisk;
    private final String firstSeen;

    public Email(
            @JsonProperty("is_free") Boolean isFree,
            @JsonProperty("is_high_risk") Boolean isHighRisk,
            @JsonProperty("first_seen") String firstSeen
    ) {
        this.isFree = isFree;
        this.isHighRisk = isHighRisk;
        this.firstSeen = firstSeen;
    }

    public Email(
            Boolean isFree,
            Boolean isHighRisk
    ) {
        this(isFree,isHighRisk,null);
    }

    public Email() {
        this(null, null, null);
    }

    /**
     * @return True if the email address is for a free email service provider.
     */
    @JsonProperty("is_free")
    public Boolean isFree() {
        return isFree;
    }

    /**
     * @return True if the email address is associated with fraud.
     */
    @JsonProperty("is_high_risk")
    public Boolean isHighRisk() {
        return isHighRisk;
    }

    /**
     * @return A date string (e.g. 2017-04-24) to identify the date an email
     *         address was first seen by MaxMind. This is expressed using the
     *         ISO 8601 date format.
     */
    @JsonProperty("first_seen")
    public String getFirstSeen() {
        return firstSeen;
    }
}
