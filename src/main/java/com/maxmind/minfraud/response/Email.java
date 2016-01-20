package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the email address.
 */
public final class Email {
    @JsonProperty("is_free")
    private final Boolean isFree;
    @JsonProperty("is_high_risk")
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Email{");
        sb.append("is_free=").append(this.isFree);
        sb.append(", is_high_risk=").append(this.isHighRisk);
        sb.append('}');
        return sb.toString();
    }
}
