package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * This class provides a model for the minFraud Factors response.
 */
public final class FactorsResponse extends InsightsResponse {

    private final Subscores subscores;

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
