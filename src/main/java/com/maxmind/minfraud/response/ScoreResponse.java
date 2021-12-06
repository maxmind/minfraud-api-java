package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the minFraud Score response.
 */
public class ScoreResponse extends AbstractModel {
    private final Disposition disposition;
    private final Double fundsRemaining;
    private final UUID id;
    private final Integer queriesRemaining;
    private final Double riskScore;
    private final List<Warning> warnings;
    private final ScoreIpAddress ipAddress;

    public ScoreResponse(
            @JsonProperty("disposition") Disposition disposition,
            @JsonProperty("funds_remaining") Double fundsRemaining,
            @JsonProperty("id") UUID id,
            @JsonProperty("ip_address") ScoreIpAddress ipAddress,
            @JsonProperty("queries_remaining") Integer queriesRemaining,
            @JsonProperty("risk_score") Double riskScore,
            @JsonProperty("warnings") List<Warning> warnings
    ) {
        this.disposition = disposition == null ? new Disposition() : disposition;
        this.fundsRemaining = fundsRemaining;
        this.id = id;
        this.ipAddress = ipAddress == null ? new ScoreIpAddress() : ipAddress;
        this.queriesRemaining = queriesRemaining;
        this.riskScore = riskScore;
        this.warnings = Collections.unmodifiableList(warnings == null ? new ArrayList<>() : warnings);
    }

    /**
     * @return The disposition set by your custom rules.
     */
    @JsonProperty("disposition")
    public final Disposition getDisposition() {
        return disposition;
    }


    /**
     * @return The approximate US dollar value of the funds remaining on your
     * MaxMind account.
     */
    @JsonProperty("funds_remaining")
    public final Double getFundsRemaining() {
        return fundsRemaining;
    }

    /**
     * @return This is a UUID that identifies the minFraud request.
     */
    public final UUID getId() {
        return id;
    }

    /**
     * @return The {@code IpAddress} model object.
     */
    @JsonProperty("ip_address")
    public IpAddressInterface getIpAddress() {
        return ipAddress;
    }

    /**
     * @return The approximate number of queries remaining for this service
     * before your account runs out of funds.
     */
    @JsonProperty("queries_remaining")
    public final Integer getQueriesRemaining() {
        return queriesRemaining;
    }

    /**
     * @return This returns the risk score, from 0.01 to 99. A higher score
     * indicates a higher risk of fraud. For example, a score of 20 indicates a
     * 20% chance that a transaction is fraudulent. We never return a risk score
     * of 0, since all transactions have the possibility of being fraudulent.
     * Likewise, we never return a risk score of 100.
     */
    @JsonProperty("risk_score")
    public final Double getRiskScore() {
        return riskScore;
    }

    /**
     * @return An unmodifiable list containing warning objects that detail
     * issues with the request such as invalid or unknown inputs. It is
     * highly recommended that you check this list for issues when integrating
     * the web service.
     */
    public final List<Warning> getWarnings() {
        return warnings;
    }
}
