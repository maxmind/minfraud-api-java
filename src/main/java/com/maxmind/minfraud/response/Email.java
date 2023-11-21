package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;
import java.time.LocalDate;

/**
 * This class contains minFraud response data related to the email address.
 */
public final class Email extends AbstractModel {
    private final Boolean isDisposable;
    private final Boolean isFree;
    private final Boolean isHighRisk;
    private final String firstSeen;
    private final EmailDomain domain;

    public Email(
            @JsonProperty("domain") EmailDomain domain,
            @JsonProperty("is_disposable") Boolean isDisposable,
            @JsonProperty("is_free") Boolean isFree,
            @JsonProperty("is_high_risk") Boolean isHighRisk,
            @JsonProperty("first_seen") String firstSeen
    ) {
        this.domain = domain == null ? new EmailDomain() : domain;
        this.isDisposable = isDisposable;
        this.isFree = isFree;
        this.isHighRisk = isHighRisk;
        this.firstSeen = firstSeen;
    }

    /**
     * @deprecated This constructor only exists for backward compatibility
     * and will be removed in the next major release.
     *
     * @param isDisposable Whether it is a disposable email.
     * @param isFree Whether it is a free email.
     * @param isHighRisk Whether it is a high risk email.
     * @param firstSeen When the email was first seen.
     */
    public Email(
            Boolean isDisposable,
            Boolean isFree,
            Boolean isHighRisk,
            String firstSeen
    ) {
        this(null, isDisposable, isFree, isHighRisk, firstSeen);
    }

    /**
     * @deprecated This constructor only exists for backward compatibility
     * and will be removed in the next major release.
     *
     * @param isFree Whether it is a free email.
     * @param isHighRisk Whether it is a high risk email.
     * @param firstSeen When the email was first seen.
     */
    public Email(
            Boolean isFree,
            Boolean isHighRisk,
            String firstSeen
    ) {
        this(null, isFree, isHighRisk, firstSeen);
    }

    /**
     * @deprecated This constructor only exists for backward compatibility
     * and will be removed in the next major release.
     *
     * @param isFree Whether it is a free email.
     * @param isHighRisk Whether it is a high risk email.
     */
    public Email(
            Boolean isFree,
            Boolean isHighRisk
    ) {
        this(null, isFree, isHighRisk, null);
    }

    public Email() {
        this(null, null, null, null, null);
    }

    /**
     * @return The {@code EmailDomain} model object.
     */
    public EmailDomain getDomain() {
        return domain;
    }

    /**
     * @return Whether the email address is from a disposable email provider.
     * If no email address was passed, this will be {@code null}.
     */
    @JsonProperty("is_disposable")
    public Boolean isDisposable() {
        return isDisposable;
    }

    /**
     * /**
     *
     * @return Whether the email address is from a free email provider such as
     * Gmail. If no email address was passed, this will be {@code null}.
     */
    @JsonProperty("is_free")
    public Boolean isFree() {
        return isFree;
    }

    /**
     * @return Whether the email address is associated with fraud. If no email
     * address was passed, this will be {@code null}.
     */
    @JsonProperty("is_high_risk")
    public Boolean isHighRisk() {
        return isHighRisk;
    }

    /**
     * @return A date string (e.g. 2017-04-24) to identify the date an email
     * address was first seen by MaxMind. This is expressed using the
     * ISO 8601 date format.
     */
    @JsonProperty("first_seen")
    public String getFirstSeen() {
        return firstSeen;
    }

    /**
     * @return A date to identify the date an email address was first seen by
     * MaxMind.
     */
    @JsonIgnore
    public LocalDate getFirstSeenDate() {
        if (firstSeen == null) {
            return null;
        }
        return LocalDate.parse(firstSeen);
    }
}
