package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.time.LocalDate;

/**
 * This class contains minFraud response data related to the email domain.
 *
 * @param classification A classification of the email domain. Possible values are: business,
 *                       education, government, isp_email.
 * @param firstSeen      The date an email domain was first seen by MaxMind.
 * @param risk           A risk score associated with the email domain, ranging from 0.01 to 99.
 *                       Higher scores indicate higher risk.
 * @param visit          An {@code EmailDomainVisit} object containing information about an
 *                       automated visit to the email domain.
 * @param volume         The activity on the email domain across the minFraud network, expressed in
 *                       sightings per million. This value ranges from 0.001 to 1,000,000.
 */
public record EmailDomain(
    @JsonProperty("classification")
    Classification classification,

    @JsonProperty("first_seen")
    LocalDate firstSeen,

    @JsonProperty("risk")
    Double risk,

    @JsonProperty("visit")
    EmailDomainVisit visit,

    @JsonProperty("volume")
    Double volume
) implements JsonSerializable {

    /**
     * The classification of an email domain.
     */
    public enum Classification {
        /**
         * A business email domain.
         */
        BUSINESS,

        /**
         * An educational institution email domain.
         */
        EDUCATION,

        /**
         * A government email domain.
         */
        GOVERNMENT,

        /**
         * An ISP-provided email domain (e.g., gmail.com, yahoo.com).
         */
        ISP_EMAIL;

        /**
         * @return a string representation of the classification in lowercase with underscores.
         */
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Compact canonical constructor that sets defaults for null values.
     */
    public EmailDomain {
        visit = visit != null ? visit : new EmailDomainVisit();
    }

    /**
     * Constructs an instance of {@code EmailDomain} with no data.
     */
    public EmailDomain() {
        this(null, null, null, null, null);
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
