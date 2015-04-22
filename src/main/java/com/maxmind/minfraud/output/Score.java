package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;

public class Score {
    @JsonProperty("credits_remaining")
    protected Integer creditsRemaining;

    protected String id;

    @JsonProperty("risk_score")
    protected Double riskScore;

    protected Warning[] warnings = new Warning[0];

    public Integer getCreditsRemaining() {
        return this.creditsRemaining;
    }

    public String getId() {
        return this.id;
    }

    public Double getRiskScore() {
        return this.riskScore;
    }

    public Warning[] getWarnings() {
        return this.warnings;
    }

    @Override
    public String toString() {
        return "Score{" +
                "creditsRemaining=" + creditsRemaining +
                ", id='" + id + '\'' +
                ", riskScore=" + riskScore +
                ", warnings=" + Arrays.toString(warnings) +
                '}';
    }

    /**
     * @return JSON representation of this object. The structure is the same as
     * the JSON provided by the GeoIP2 web service.
     * @throws IOException if there is an error serializing the object to JSON.
     */
    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return mapper.writeValueAsString(this);
    }

}
