package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.time.LocalDate;

/**
 * This class contains minFraud response data related to the email domain.
 *
 * @param firstSeen A date to identify the date an email domain was first seen by MaxMind.
 */
public record EmailDomain(
    @JsonProperty("first_seen")
    LocalDate firstSeen
) implements JsonSerializable {

    /**
     * Constructs an instance of {@code EmailDomain} with no data.
     */
    public EmailDomain() {
        this(null);
    }

    /**
     * @return A date to identify the date an email domain was first seen by MaxMind.
     * @deprecated Use {@link #firstSeen()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("first_seen")
    public LocalDate getFirstSeen() {
        return firstSeen();
    }
}
