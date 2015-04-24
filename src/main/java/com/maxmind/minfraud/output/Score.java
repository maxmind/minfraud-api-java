package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Score {
    @JsonProperty("credits_remaining")
    protected Integer creditsRemaining;

    protected String id;

    @JsonProperty("risk_score")
    protected Double riskScore;

    protected List<Warning> warnings = new ArrayList<>();

    public final Integer getCreditsRemaining() {
        return creditsRemaining;
    }

    public final String getId() {
        return id;
    }

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
        StringBuilder sb = new StringBuilder("Score{");
        sb.append("creditsRemaining=").append(this.creditsRemaining);
        sb.append(", id='").append(this.id).append('\'');
        sb.append(", riskScore=").append(this.riskScore);
        sb.append(", warnings=").append(this.warnings);
        sb.append('}');
        return sb.toString();
    }
}
