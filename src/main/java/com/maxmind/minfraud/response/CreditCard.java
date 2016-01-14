package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the credit card.
 */
public final class CreditCard {
    private final Issuer issuer;
    private final String country;
    private final Boolean isIssuedInBillingAddressCountry;
    private final Boolean isPrepaid;

    public CreditCard(
            @JsonProperty("country") String country,
            @JsonProperty("is_issued_in_billing_address_country") Boolean isIssuedInBillingAddressCountry,
            @JsonProperty("is_prepaid") Boolean isPrepaid,
            @JsonProperty("issuer") Issuer issuer
    ) {
        this.country = country;
        this.isIssuedInBillingAddressCountry = isIssuedInBillingAddressCountry;
        this.isPrepaid = isPrepaid;
        this.issuer = issuer == null ? new Issuer() : issuer;
    }

    public CreditCard() {
        this(null, null, null, null);
    }

    /**
     * @return The {@code Issuer} model object.
     */
    public Issuer getIssuer() {
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
    public String getCountry() {
        return country;
    }

    /**
     * @return True if the country of the billing address matches the country
     * of the majority of customers using that IIN. In cases where the
     * location of customers is highly mixed, the match is to the country of
     * the bank issuing the card.
     */
    @JsonProperty("is_issued_in_billing_address_country")
    public Boolean isIssuedInBillingAddressCountry() {
        return isIssuedInBillingAddressCountry;
    }

    /**
     * @return True if the card is a prepaid card. False if not prepaid. If
     * the IIN was not provided or is unknown, null will be returned.
     */
    @JsonProperty("is_prepaid")
    public Boolean isPrepaid() {
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
