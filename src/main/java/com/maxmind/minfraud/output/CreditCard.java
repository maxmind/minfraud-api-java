package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditCard {
    protected Issuer issuer = new Issuer();
    protected String country;

    @JsonProperty("is_issued_in_billing_address_country")
    protected Boolean isIssuedInBillingAddressCountry;

    @JsonProperty("is_prepaid")
    protected Boolean isPrepaid;

    public Issuer getIssuer() {
        return this.issuer;
    }

    public String getCountry() {
        return this.country;
    }

    public Boolean isIssuedInBillingAddressCountry() {
        return this.isIssuedInBillingAddressCountry;
    }

    public Boolean isPrepaid() {
        return this.isPrepaid;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "issuer=" + issuer +
                ", country='" + country + '\'' +
                ", isIssuedInBillingAddressCountry=" + isIssuedInBillingAddressCountry +
                ", isPrepaid=" + isPrepaid +
                '}';
    }
}
