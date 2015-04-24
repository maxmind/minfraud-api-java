package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShippingAddress extends AbstractAddress {
    @JsonProperty("is_high_risk")
    protected Boolean isHighRisk;

    @JsonProperty("distance_to_billing_address")
    protected Integer distanceToBillingAddress;

    @JsonIgnore
    public final Boolean isHighRisk() {
        return isHighRisk;
    }

    public final Integer getDistanceToBillingAddress() {
        return distanceToBillingAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ShippingAddress{");
        sb.append("isHighRisk=").append(this.isHighRisk);
        sb.append(", distanceToBillingAddress=").append(this.distanceToBillingAddress);
        sb.append(", highRisk=").append(this.isHighRisk());
        sb.append('}');
        return sb.toString();
    }
}

