package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Insights extends Score {
    @JsonProperty("ip_location")
    protected IpLocation ipLocation;

    @JsonProperty("credit_card")
    protected CreditCard creditCard = new CreditCard();

    @JsonProperty("shipping_address")
    protected ShippingAddress shippingAddress = new ShippingAddress();

    @JsonProperty("billing_address")
    protected BillingAddress billingAddress = new BillingAddress();

    public IpLocation getIpLocation() {
        return this.ipLocation;
    }


    public CreditCard getCreditCard() {
        return this.creditCard;
    }

    public ShippingAddress getShippingAddress() {
        return this.shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return this.billingAddress;
    }

    @Override
    public String toString() {
        return "Insights{" +
//                "ipLocation=" + ipLocation +
                ", creditCard=" + creditCard +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", creditsRemaining=" + creditsRemaining +
                ", id='" + id + '\'' +
                ", riskScore=" + riskScore +
                ", warnings=" + warnings +
                '}';
    }
}
