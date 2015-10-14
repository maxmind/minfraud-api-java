package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the credit card.
 */
public final class CreditCard {
    protected Issuer issuer = new Issuer();
    protected String country;

    @JsonProperty("is_issued_in_billing_address_country")
    protected Boolean isIssuedInBillingAddressCountry;

    @JsonProperty("is_prepaid")
    protected Boolean isPrepaid;

    /**
     * @return The {@code Issuer} model object.
     */
    public final Issuer getIssuer() {
        return issuer;
    }

    /**
     * @return The two letter <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">
     * ISO 3166-1 alpha-2</a> country code associated with the location
     * of the majority of customers using this credit card as determined
     * by their billing address. In cases where the location of customers
     * is highly mixed, this defaults to the country of the bank issuing
     * the card.
     */
    public final String getCountry() {
        return country;
    }

    /**
     * @return True if the country of the billing address matches the country
     * of the majority of customers using that IIN. In cases where the
     * location of customers is highly mixed, the match is to the country of
     * the bank issuing the card.
     */
    @JsonIgnore
    public final Boolean isIssuedInBillingAddressCountry() {
        return isIssuedInBillingAddressCountry;
    }

    /**
     * @return True if the card is a prepaid card. False if not prepaid. If
     * the IIN was not provided or is unknown, null will be returned.
     */
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
