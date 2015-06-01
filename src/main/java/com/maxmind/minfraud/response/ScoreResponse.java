package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the minFraud Score response.
 */
public class ScoreResponse {
    @JsonProperty("credits_remaining")
    protected Integer creditsRemaining;

    protected String id;

    @JsonProperty("risk_score")
    protected Double riskScore;

    protected List<Warning> warnings = new ArrayList<>();

    /**
     * @return The approximate number of service credits remaining on your
     * account.
     */
    public final Integer getCreditsRemaining() {
        return creditsRemaining;
    }

    /**
     * @return This is a UUID that identifies the minFraud request.
     */
    public final String getId() {
        return id;
    }

    /**
     * @return This returns the risk score, from 0.01 to 99. A higher score
     * indicates a higher risk of fraud. For example, a score of 20 indicates a
     * 20% chance that a transaction is fraudulent. We never return a risk score
     * of 0, since all transactions have the possibility of being fraudulent.
     * Likewise we never return a risk score of 100.
     */
    public final Double getRiskScore() {
        return riskScore;
    }

    public final List<Warning> getWarnings() {
        return new ArrayList<>(warnings);
    }

    /**
     * @return JSON representation of this object. The structure is the same as
     * the JSON provided by the GeoIP2 web service.
     * @throws IOException if there is an error serializing the object to JSON.
     */
    public final String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setSerializationInclusion(Include.NON_EMPTY);

        return mapper.writeValueAsString(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ScoreResponse{");
        sb.append("creditsRemaining=").append(this.creditsRemaining);
        sb.append(", id='").append(this.id).append('\'');
        sb.append(", riskScore=").append(this.riskScore);
        sb.append(", warnings=").append(this.warnings);
        sb.append('}');
        return sb.toString();
    }
}
