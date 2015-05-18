package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class provides a model for the minFraud Insights response.
 */
public final class InsightsResponse extends ScoreResponse {
    @JsonProperty("ip_location")
    protected IpLocation ipLocation;

    @JsonProperty("credit_card")
    protected CreditCard creditCard = new CreditCard();

    @JsonProperty("shipping_address")
    protected ShippingAddress shippingAddress = new ShippingAddress();

    @JsonProperty("billing_address")
    protected BillingAddress billingAddress = new BillingAddress();

    /**
     * @return The {@code IpLocation} model object.
     */
    public final IpLocation getIpLocation() {
        return ipLocation;
    }

    /**
     * @return The {@code CreditCard} model object.
     */
    public final CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @return The {@code ShippingAddress} model object.
     */
    public final ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @return The {@code BillingAddress} model object.
     */
    public final BillingAddress getBillingAddress() {
        return billingAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InsightsResponse{");
        sb.append("creditsRemaining=").append(this.creditsRemaining);
        sb.append(", id='").append(this.id).append('\'');
        sb.append(", riskScore=").append(this.riskScore);
        sb.append(", warnings=").append(this.warnings);
        sb.append(", ipLocation=").append(this.ipLocation);
        sb.append(", creditCard=").append(this.creditCard);
        sb.append(", shippingAddress=").append(this.shippingAddress);
        sb.append(", billingAddress=").append(this.billingAddress);
        sb.append('}');
        return sb.toString();
    }
}
