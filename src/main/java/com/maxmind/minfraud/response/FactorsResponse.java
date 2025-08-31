package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;

/**
 * This class provides a model for the minFraud Factors response.
 */
public final class FactorsResponse extends InsightsResponse {

    private final List<RiskScoreReason> riskScoreReasons;


    /**
     * Constructor for {@code FactorsResponse}.
     *
     * @param billingAddress   The {@code BillingAddress} model object.
     * @param creditCard       The {@code CreditCard} model object.
     * @param device           The {@code Device} model object.
     * @param disposition      The {@code Disposition} model object.
     * @param email            The {@code Email} model object.
     * @param fundsRemaining   The approximate US dollar value of the funds.
     * @param id               This is a UUID that identifies the minFraud request.
     * @param ipAddress        The {@code IpAddress} model object.
     * @param queriesRemaining The number of queries remaining.
     * @param riskScore        The risk score.
     * @param shippingAddress  The {@code ShippingAddress} model object.
     * @param riskScoreReasons A list containing {@code RiskScoreReason} model objects that
     *     describe risk score reasons for a given transaction that change the risk score
     *     significantly. Risk score reasons are usually only returned for medium to high risk
     *     transactions. If there were no significant changes to the risk score due to these
     *     reasons, then this list will be empty.
     * @param warnings         A list containing warning objects.
     */
    public FactorsResponse(
        @JsonProperty("billing_address") BillingAddress billingAddress,
        @JsonProperty("billing_phone") Phone billingPhone,
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
        @JsonProperty("shipping_phone") Phone shippingPhone,
        @JsonProperty("risk_score_reasons") List<RiskScoreReason> riskScoreReasons,
        @JsonProperty("warnings") List<Warning> warnings
    ) {
        super(billingAddress, billingPhone, creditCard, device, disposition, email,
            fundsRemaining, id, ipAddress, queriesRemaining, riskScore,
            shippingAddress, shippingPhone, warnings);
        this.riskScoreReasons = riskScoreReasons;
    }


    /**
     * @return A list containing objects that describe risk score reasons
     *     for a given transaction that change the risk score significantly.
     */
    @JsonProperty("risk_score_reasons")
    public List<RiskScoreReason> getRiskScoreReasons() {
        return riskScoreReasons;
    }

}
