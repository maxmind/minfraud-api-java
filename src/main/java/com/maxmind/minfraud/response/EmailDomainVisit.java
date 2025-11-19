package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.time.LocalDate;

/**
 * This class contains information about an automated visit to the email domain.
 *
 * @param hasRedirect   Whether the domain redirects to another URL. This field is only present if
 *                      the value is true.
 * @param lastVisitedOn The date when the automated visit was last completed.
 * @param status        The status of the domain based on the automated visit. Possible values are:
 *                      live, dns_error, network_error, http_error, parked, pre_development.
 */
public record EmailDomainVisit(
    @JsonProperty("has_redirect")
    Boolean hasRedirect,

    @JsonProperty("last_visited_on")
    LocalDate lastVisitedOn,

    @JsonProperty("status")
    Status status
) implements JsonSerializable {

    /**
     * The status of an email domain based on an automated visit.
     */
    public enum Status {
        /**
         * The domain is live and responding normally.
         */
        LIVE,

        /**
         * A DNS error occurred when attempting to visit the domain.
         */
        DNS_ERROR,

        /**
         * A network error occurred when attempting to visit the domain.
         */
        NETWORK_ERROR,

        /**
         * An HTTP error occurred when attempting to visit the domain.
         */
        HTTP_ERROR,

        /**
         * The domain is parked.
         */
        PARKED,

        /**
         * The domain is in pre-development.
         */
        PRE_DEVELOPMENT;

        /**
         * @return a string representation of the status in lowercase with underscores.
         */
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Constructs an instance of {@code EmailDomainVisit} with no data.
     */
    public EmailDomainVisit() {
        this(null, null, null);
    }
}
