package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditCard {
    protected Issuer issuer = new Issuer();
    protected String country;

    @JsonProperty("is_issued_in_billing_address_country")
    protected Boolean isIssuedInBillingAddressCountry;

    @JsonProperty("is_prepaid")
    protected Boolean isPrepaid;

    public final Issuer getIssuer() {
        return issuer;
    }

    public final String getCountry() {
        return country;
    }

    @JsonIgnore
    public final Boolean isIssuedInBillingAddressCountry() {
        return isIssuedInBillingAddressCountry;
    }

    @JsonIgnore
    public final Boolean isPrepaid() {
        return isPrepaid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CreditCard{");
        sb.append("issuer=").append(this.issuer);
        sb.append(", country='").append(this.country).append('\'');
        sb.append(", isIssuedInBillingAddressCountry=").append(this.isIssuedInBillingAddressCountry);
        sb.append(", isPrepaid=").append(this.isPrepaid);
        sb.append('}');
        return sb.toString();
    }
}
