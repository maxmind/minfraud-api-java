package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.time.LocalDate;

/**
 * This class contains minFraud response data related to the email address.
 *
 * @param domain       The {@code EmailDomain} model object.
 * @param isDisposable Whether it is a disposable email.
 * @param isFree       Whether it is a free email.
 * @param isHighRisk   Whether it is a high risk email.
 * @param firstSeen    A date string (e.g. 2017-04-24) to identify the date an email address was
 *                     first seen by MaxMind. This is expressed using the ISO 8601 date format.
 */
public record Email(
    @JsonProperty("domain")
    EmailDomain domain,

    @JsonProperty("is_disposable")
    Boolean isDisposable,

    @JsonProperty("is_free")
    Boolean isFree,

    @JsonProperty("is_high_risk")
    Boolean isHighRisk,

    @JsonProperty("first_seen")
    String firstSeen
) implements JsonSerializable {

    /**
     * Compact canonical constructor that sets defaults for null values.
     */
    public Email {
        domain = domain != null ? domain : new EmailDomain();
    }

    /**
     * Constructs an instance of {@code Email} with no data.
     */
    public Email() {
        this(null, null, null, null, null);
    }

    /**
     * @return The {@code EmailDomain} model object.
     * @deprecated Use {@link #domain()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("domain")
    public EmailDomain getDomain() {
        return domain();
    }

    /**
     * @return A date string (e.g. 2017-04-24) to identify the date an email address was first seen
     *     by MaxMind. This is expressed using the ISO 8601 date format.
     * @deprecated Use {@link #firstSeen()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("first_seen")
    public String getFirstSeen() {
        return firstSeen();
    }

    /**
     * @return A date to identify the date an email address was first seen by MaxMind.
     */
    @JsonIgnore
    public LocalDate getFirstSeenDate() {
        if (firstSeen == null) {
            return null;
        }
        return LocalDate.parse(firstSeen);
    }
}
