package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class contains minFraud response data related to the credit card.
 *
 * @param brand                           The credit card brand.
 * @param country                         The two letter <a
 *                                        href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">
 *                                        ISO 3166-1 alpha-2</a> country code associated with the
 *                                        location of the majority of customers using this credit
 *                                        card as determined by their billing address. In cases
 *                                        where the location of customers is highly mixed, this
 *                                        defaults to the country of the bank issuing the card.
 * @param isBusiness                      True if the card is a business card. False if not a
 *                                        business card. If the IIN was not provided or is unknown,
 *                                        null will be returned.
 * @param isIssuedInBillingAddressCountry True if the country of the billing address matches the
 *                                        country of the majority of customers using that IIN. In
 *                                        cases where the location of customers is highly mixed, the
 *                                        match is to the country of the bank issuing the card.
 * @param isPrepaid                       True if the card is a prepaid card. False if not prepaid.
 *                                        If the IIN was not provided or is unknown, null will be
 *                                        returned.
 * @param isVirtual                       True if the card is a virtual card. False if not virtual.
 *                                        If the IIN was not provided or is unknown, null will be
 *                                        returned.
 * @param issuer                          The {@code Issuer} model object.
 * @param type                            The credit card type.
 */
public record CreditCard(
    @JsonProperty("brand")
    String brand,

    @JsonProperty("country")
    String country,

    @JsonProperty("is_business")
    Boolean isBusiness,

    @JsonProperty("is_issued_in_billing_address_country")
    Boolean isIssuedInBillingAddressCountry,

    @JsonProperty("is_prepaid")
    Boolean isPrepaid,

    @JsonProperty("is_virtual")
    Boolean isVirtual,

    @JsonProperty("issuer")
    Issuer issuer,

    @JsonProperty("type")
    String type
) implements JsonSerializable {

    /**
     * Compact canonical constructor that sets defaults for null values.
     */
    public CreditCard {
        issuer = issuer != null ? issuer : new Issuer();
    }

    /**
     * Constructs an instance of {@code CreditCard} with no data.
     */
    public CreditCard() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * @return The {@code Issuer} model object.
     * @deprecated Use {@link #issuer()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("issuer")
    public Issuer getIssuer() {
        return issuer();
    }

    /**
     * @return The credit card brand.
     * @deprecated Use {@link #brand()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("brand")
    public String getBrand() {
        return brand();
    }

    /**
     * @return The two letter <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"> ISO 3166-1
     *     alpha-2</a> country code associated with the location of the majority of customers using
     *     this credit card as determined by their billing address. In cases where the location of
     *     customers is highly mixed, this defaults to the country of the bank issuing the card.
     * @deprecated Use {@link #country()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("country")
    public String getCountry() {
        return country();
    }

    /**
     * @return The credit card type.
     * @deprecated Use {@link #type()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("type")
    public String getType() {
        return type();
    }
}
