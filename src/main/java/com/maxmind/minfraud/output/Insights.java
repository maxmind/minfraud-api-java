package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Insights extends Score {
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
        StringBuilder sb = new StringBuilder("Insights{");
        sb.append("ipLocation=").append(this.ipLocation);
        sb.append(", creditCard=").append(this.creditCard);
        sb.append(", shippingAddress=").append(this.shippingAddress);
        sb.append(", billingAddress=").append(this.billingAddress);
        sb.append('}');
        return sb.toString();
    }
}
