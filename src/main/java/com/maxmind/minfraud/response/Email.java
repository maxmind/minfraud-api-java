package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class contains minFraud response data related to the email address.
 */
public final class Email extends AbstractModel {
    private final Boolean isFree;
    private final Boolean isHighRisk;

    public Email(
            @JsonProperty("is_free") Boolean isFree,
            @JsonProperty("is_high_risk") Boolean isHighRisk
    ) {
        this.isFree = isFree;
        this.isHighRisk = isHighRisk;
    }

    public Email() {
        this(null, null);
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
}
