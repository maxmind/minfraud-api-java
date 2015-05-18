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
     * @return This field contains an ISO 3166-1 alpha-2 country code
     * representing the country that the card was issued in. This will be null
     * if there is no value in the response.
     */
    public final String getCountry() {
        return country;
    }

    /**
     * @return This will return true if the country of the billing address
     * matches the country that the credit card was issued in. It will return
     * false if they do not match. If the billing country was not provided or
     * the issuer country could not be determined, null will be returned.
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
