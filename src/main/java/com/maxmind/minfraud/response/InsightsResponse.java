package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.util.List;
import java.util.UUID;

/**
 * This class provides a model for the minFraud Insights response.
 *
 * @param billingAddress   The {@code BillingAddress} model object.
 * @param billingPhone     The {@code Phone} model object for the billing phone number.
 * @param creditCard       The {@code CreditCard} model object.
 * @param device           The {@code Device} model object.
 * @param disposition      The disposition set by your custom rules.
 * @param email            The {@code Email} model object.
 * @param fundsRemaining   The approximate US dollar value of the funds remaining on your MaxMind
 *                         account.
 * @param id               This is a UUID that identifies the minFraud request.
 * @param ipAddress        The {@code IpAddress} model object.
 * @param queriesRemaining The approximate number of queries remaining for this service before your
 *                         account runs out of funds.
 * @param riskScore        This returns the risk score, from 0.01 to 99. A higher score indicates
 *                         a higher risk of fraud. For example, a score of 20 indicates a 20%
 *                         chance that a transaction is fraudulent. We never return a risk score of
 *                         0, since all transactions have the possibility of being fraudulent.
 *                         Likewise, we never return a risk score of 100.
 * @param shippingAddress  The {@code ShippingAddress} model object.
 * @param shippingPhone    The {@code Phone} model object for the shipping phone number.
 * @param warnings         An unmodifiable list containing warning objects that detail issues with
 *                         the request such as invalid or unknown inputs. It is highly recommended
 *                         that you check this list for issues when integrating the web service.
 */
public record InsightsResponse(
    @JsonProperty("billing_address")
    BillingAddress billingAddress,

    @JsonProperty("billing_phone")
    Phone billingPhone,

    @JsonProperty("credit_card")
    CreditCard creditCard,

    @JsonProperty("device")
    Device device,

    @JsonProperty("disposition")
    Disposition disposition,

    @JsonProperty("email")
    Email email,

    @JsonProperty("funds_remaining")
    Double fundsRemaining,

    @JsonProperty("id")
    UUID id,

    @JsonProperty("ip_address")
    IpAddress ipAddress,

    @JsonProperty("queries_remaining")
    Integer queriesRemaining,

    @JsonProperty("risk_score")
    Double riskScore,

    @JsonProperty("shipping_address")
    ShippingAddress shippingAddress,

    @JsonProperty("shipping_phone")
    Phone shippingPhone,

    @JsonProperty("warnings")
    List<Warning> warnings
) implements JsonSerializable {

    /**
     * Compact canonical constructor that sets defaults for null values.
     */
    public InsightsResponse {
        billingAddress = billingAddress != null ? billingAddress : new BillingAddress();
        billingPhone = billingPhone != null ? billingPhone : new Phone();
        creditCard = creditCard != null ? creditCard : new CreditCard();
        device = device != null ? device : new Device();
        disposition = disposition != null ? disposition : new Disposition();
        email = email != null ? email : new Email();
        ipAddress = ipAddress != null ? ipAddress : new IpAddress();
        shippingAddress = shippingAddress != null ? shippingAddress : new ShippingAddress();
        shippingPhone = shippingPhone != null ? shippingPhone : new Phone();
        warnings = warnings != null ? List.copyOf(warnings) : List.of();
    }

    /**
     * Constructs an instance of {@code InsightsResponse} with no data.
     */
    public InsightsResponse() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * @return The {@code BillingAddress} model object.
     * @deprecated Use {@link #billingAddress()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("billing_address")
    public BillingAddress getBillingAddress() {
        return billingAddress();
    }

    /**
     * @return The {@code Phone} model object for the billing phone number.
     * @deprecated Use {@link #billingPhone()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("billing_phone")
    public Phone getBillingPhone() {
        return billingPhone();
    }

    /**
     * @return The {@code CreditCard} model object.
     * @deprecated Use {@link #creditCard()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("credit_card")
    public CreditCard getCreditCard() {
        return creditCard();
    }

    /**
     * @return The {@code Device} model object.
     * @deprecated Use {@link #device()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("device")
    public Device getDevice() {
        return device();
    }

    /**
     * @return The disposition set by your custom rules.
     * @deprecated Use {@link #disposition()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("disposition")
    public Disposition getDisposition() {
        return disposition();
    }

    /**
     * @return The {@code Email} model object.
     * @deprecated Use {@link #email()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("email")
    public Email getEmail() {
        return email();
    }

    /**
     * @return The approximate US dollar value of the funds remaining on your MaxMind account.
     * @deprecated Use {@link #fundsRemaining()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("funds_remaining")
    public Double getFundsRemaining() {
        return fundsRemaining();
    }

    /**
     * @return This is a UUID that identifies the minFraud request.
     * @deprecated Use {@link #id()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("id")
    public UUID getId() {
        return id();
    }

    /**
     * @return The {@code IpAddress} model object.
     * @deprecated Use {@link #ipAddress()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("ip_address")
    public IpAddress getIpAddress() {
        return ipAddress();
    }

    /**
     * @return The approximate number of queries remaining for this service before your account runs
     *     out of funds.
     * @deprecated Use {@link #queriesRemaining()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("queries_remaining")
    public Integer getQueriesRemaining() {
        return queriesRemaining();
    }

    /**
     * @return This returns the risk score, from 0.01 to 99. A higher score indicates a higher risk
     *     of fraud. For example, a score of 20 indicates a 20% chance that a transaction is
     *     fraudulent. We never return a risk score of 0, since all transactions have the
     *     possibility of being fraudulent. Likewise, we never return a risk score of 100.
     * @deprecated Use {@link #riskScore()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("risk_score")
    public Double getRiskScore() {
        return riskScore();
    }

    /**
     * @return The {@code ShippingAddress} model object.
     * @deprecated Use {@link #shippingAddress()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("shipping_address")
    public ShippingAddress getShippingAddress() {
        return shippingAddress();
    }

    /**
     * @return The {@code Phone} model object for the shipping phone number.
     * @deprecated Use {@link #shippingPhone()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("shipping_phone")
    public Phone getShippingPhone() {
        return shippingPhone();
    }

    /**
     * @return An unmodifiable list containing warning objects that detail issues with the request
     *     such as invalid or unknown inputs. It is highly recommended that you check this list for
     *     issues when integrating the web service.
     * @deprecated Use {@link #warnings()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("warnings")
    public List<Warning> getWarnings() {
        return warnings();
    }
}
