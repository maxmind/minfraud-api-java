package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsightsResponse extends ScoreResponse {
    @JsonProperty("ip_location")
    protected IpLocation ipLocation;

    @JsonProperty("credit_card")
    protected CreditCard creditCard = new CreditCard();

    @JsonProperty("shipping_address")
    protected ShippingAddress shippingAddress = new ShippingAddress();

    @JsonProperty("billing_address")
    protected BillingAddress billingAddress = new BillingAddress();

    public final IpLocation getIpLocation() {
        return ipLocation;
    }


    public final CreditCard getCreditCard() {
        return creditCard;
    }

    public final ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

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
