package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;

/**
 * This class provides a model for the minFraud Factors response.
 */
public final class FactorsResponse extends InsightsResponse {

    private final Subscores subscores;

    
    /**
     * Constructor for {@code FactorsResponse}.
     *
     * @param billingAddress The {@code BillingAddress} model object.
     * @param creditCard The {@code CreditCard} model object.
     * @param device The {@code Device} model object.
     * @param disposition The {@code Disposition} model object.
     * @param email The {@code Email} model object.
     * @param fundsRemaining The approximate US dollar value of the funds.
     * @param id This is a UUID that identifies the minFraud request.
     * @param ipAddress The {@code IpAddress} model object.
     * @param queriesRemaining The number of queries remaining.
     * @param riskScore The risk score.
     * @param shippingAddress The {@code ShippingAddress} model object.
     * @param subscores The {@code Subscores} model object.
     * @param warnings An list containing warning objects.
     */
    public FactorsResponse(
        @JsonProperty("billing_address") BillingAddress billingAddress,
        @JsonProperty("credit_card") CreditCard creditCard,
        @JsonProperty("device") Device device,
        @JsonProperty("disposition") Disposition disposition,
        @JsonProperty("email") Email email,
        @JsonProperty("funds_remaining") Double fundsRemaining,
        @JsonProperty("id") UUID id,
        @JsonProperty("ip_address") IpAddress ipAddress,
        @JsonProperty("queries_remaining") Integer queriesRemaining,
        @JsonProperty("risk_score") Double riskScore,
        @JsonProperty("shipping_address") ShippingAddress shippingAddress,
        @JsonProperty("subscores") Subscores subscores,
        @JsonProperty("warnings") List<Warning> warnings
    ) {
        super(billingAddress, creditCard, device, disposition, email,
            fundsRemaining, id, ipAddress, queriesRemaining, riskScore,
            shippingAddress, warnings);
        this.subscores = subscores;
    }

    /**
     * @return The {@code Subscores} model object containing the risk factor
     * scores.
     */
    @JsonProperty("subscores")
    public Subscores getSubscores() {
        return subscores;
    }
}
