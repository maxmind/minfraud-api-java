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
            @JsonProperty("credits_remaining") Integer creditsRemaining,
            @JsonProperty("device") Device device,
            @JsonProperty("email") Email email,
            @JsonProperty("id") UUID id,
            @JsonProperty("ip_address") IpAddress ipAddress,
            @JsonProperty("risk_score") Double riskScore,
            @JsonProperty("shipping_address") ShippingAddress shippingAddress,
            @JsonProperty("subscores") Subscores subscores,
            @JsonProperty("warnings") List<Warning> warnings
    ) {
        super(billingAddress, creditCard, creditsRemaining, device, email,
                id, ipAddress, riskScore, shippingAddress, warnings);
        this.subscores = subscores;
    }

    /**
     * @return The {@code Subscores} model object.
     */
    @JsonProperty("subscores")
    public Subscores getSubscores() {
        return subscores;
    }

}
