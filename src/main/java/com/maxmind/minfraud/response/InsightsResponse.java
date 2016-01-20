package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class provides a model for the minFraud Insights response.
 */
public final class InsightsResponse extends ScoreResponse {
    private final IpAddress ipAddress;
    private final CreditCard creditCard;
    private final Device device;
    private final Email email;
    private final ShippingAddress shippingAddress;
    private final BillingAddress billingAddress;

    public InsightsResponse(
            @JsonProperty("billing_address") BillingAddress billingAddress,
            @JsonProperty("credit_card")  CreditCard creditCard,
            @JsonProperty("device")  Device device,
            @JsonProperty("email")  Email email,
            @JsonProperty("credits_remaining") Integer creditsRemaining,
            @JsonProperty("id") String id,
            @JsonProperty("ip_address") IpAddress ipAddress,
            @JsonProperty("risk_score") Double riskScore,
            @JsonProperty("shipping_address") ShippingAddress shippingAddress,
            @JsonProperty("warnings") List<Warning> warnings
    )
    {
        super(creditsRemaining, id, riskScore, warnings);
        this.billingAddress = billingAddress == null ? new BillingAddress() : billingAddress;
        this.creditCard = creditCard == null ? new CreditCard() : creditCard;
        this.device = device == null ? new Device() : device;
        this.email = email == null ? new Email() : email;
        this.ipAddress = ipAddress == null ? new IpAddress() : ipAddress;
        this.shippingAddress = shippingAddress == null ? new ShippingAddress() : shippingAddress;
    }

    /**
     * @return The {@code IpAddress} model object.
     */
    @JsonProperty("ip_address")
    public IpAddress getIpAddress() {
        return ipAddress;
    }

    /**
     * @return The {@code CreditCard} model object.
     */
    @JsonProperty("credit_card")
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @return The {@code Device} model object.
     */
    @JsonProperty("device")
    public Device getDevice() {
        return device;
    }

    /**
     * @return The {@code Email} model object.
     */
    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }

    /**
     * @return The {@code ShippingAddress} model object.
     */
    @JsonProperty("shipping_address")
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @return The {@code BillingAddress} model object.
     */
    @JsonProperty("billing_address")
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InsightsResponse{");
        sb.append("creditsRemaining=").append(this.creditsRemaining);
        sb.append(", id='").append(this.id).append('\'');
        sb.append(", riskScore=").append(this.riskScore);
        sb.append(", warnings=").append(this.warnings);
        sb.append(", ipAddress=").append(this.ipAddress);
        sb.append(", creditCard=").append(this.creditCard);
        sb.append(", device=").append(this.device);
        sb.append(", email=").append(this.email);
        sb.append(", shippingAddress=").append(this.shippingAddress);
        sb.append(", billingAddress=").append(this.billingAddress);
        sb.append('}');
        return sb.toString();
    }
}
