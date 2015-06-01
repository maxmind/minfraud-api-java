package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the shipping address.
 */
public final class ShippingAddress extends AbstractAddress {
    @JsonProperty("is_high_risk")
    protected Boolean isHighRisk;

    @JsonProperty("distance_to_billing_address")
    protected Integer distanceToBillingAddress;

    /**
     * @return This returns true if the shipping address is an address
     * associated with fraudulent transactions. It returns false when the
     * address is not associated with increased risk. If the address could not
     * be parsed or was not provided, null is returned.
     */
    @JsonIgnore
    public final Boolean isHighRisk() {
        return isHighRisk;
    }

    /**
     * @return The distance in kilometers from the shipping address to billing
     * address.
     */
    public final Integer getDistanceToBillingAddress() {
        return distanceToBillingAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ShippingAddress{");
        sb.append("isHighRisk=").append(this.isHighRisk);
        sb.append(", distanceToBillingAddress=").append(this.distanceToBillingAddress);
        sb.append(", super:").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}

