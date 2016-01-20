package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the minFraud Score response.
 */
public class ScoreResponse {
    protected final Integer creditsRemaining;
    protected final UUID id;
    protected final Double riskScore;
    protected final List<Warning> warnings;

    public ScoreResponse(
            @JsonProperty("credits_remaining") Integer creditsRemaining,
            @JsonProperty("id") String id,
            @JsonProperty("risk_score") Double riskScore,
            @JsonProperty("warnings") List<Warning> warnings
    ) {
        this.creditsRemaining = creditsRemaining;
        this.id = id == null ? null : UUID.fromString(id);
        this.riskScore = riskScore;
        this.warnings = Collections.unmodifiableList(warnings == null ? new ArrayList<Warning>() : warnings);
    }

    /**
     * @return The approximate number of service credits remaining on your
     * account.
     */
    @JsonProperty("credits_remaining")
    public final Integer getCreditsRemaining() {
        return creditsRemaining;
    }

    /**
     * @return This is a UUID that identifies the minFraud request.
     */
    public final UUID getId() {
        return id;
    }

    /**
     * @return This returns the risk score, from 0.01 to 99. A higher score
     * indicates a higher risk of fraud. For example, a score of 20 indicates a
     * 20% chance that a transaction is fraudulent. We never return a risk score
     * of 0, since all transactions have the possibility of being fraudulent.
     * Likewise we never return a risk score of 100.
     */
    @JsonProperty("risk_score")
    public final Double getRiskScore() {
        return riskScore;
    }

    /**
     * @return An unmodifiable list contains warning objects detailing issues
     * with the request that was sent such as invalid or unknown inputs. It is
     * highly recommended that you check this list for issues when integrating
     * the web service.
     */
    public final List<Warning> getWarnings() {
        return warnings;
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
        mapper.disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);

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
