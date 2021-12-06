package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.time.LocalDate;

/**
 * This class contains minFraud response data related to the email domain.
 */
public final class EmailDomain extends AbstractModel {
    private final LocalDate firstSeen;

    public EmailDomain(
            @JsonProperty("first_seen") LocalDate firstSeen
    ) {
        this.firstSeen = firstSeen;
    }

    public EmailDomain() {
        this(null);
    }

    /**
     * @return A date to identify the date an email domain was first
     * seen by MaxMind.
     */
    @JsonProperty("first_seen")
    public LocalDate getFirstSeen() {
        return firstSeen;
    }
}
