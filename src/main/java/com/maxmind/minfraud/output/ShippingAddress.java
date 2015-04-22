package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShippingAddress extends Address {
    @JsonProperty("is_high_risk")
    protected Boolean isHighRisk;

    @JsonProperty("distance_to_billing_address")
    protected Integer distanceToBillingAddress;

    public Boolean isHighRisk() {
        return this.isHighRisk;
    }

    public Integer getDistanceToBillingAddress() {
        return this.distanceToBillingAddress;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "isPostalInCity=" + isPostalInCity +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distanceToIpLocation=" + distanceToIpLocation +
                ", isInIpCountry=" + isInIpCountry +
                "isHighRisk=" + isHighRisk +
                ", distanceToBillingAddress=" + distanceToBillingAddress +
                '}';
    }
}

