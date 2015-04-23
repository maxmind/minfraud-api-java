package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Score {
    @JsonProperty("credits_remaining")
    protected Integer creditsRemaining;

    protected String id;

    @JsonProperty("risk_score")
    protected Double riskScore;

    protected List<Warning> warnings = new ArrayList<>();

    public Integer getCreditsRemaining() {
        return this.creditsRemaining;
    }

    public String getId() {
        return this.id;
    }

    public Double getRiskScore() {
        return this.riskScore;
    }

    public List<Warning> getWarnings() {
        return new ArrayList<>(this.warnings);
    }

    @Override
    public String toString() {
        return "Score{" +
                "creditsRemaining=" + creditsRemaining +
                ", id='" + id + '\'' +
                ", riskScore=" + riskScore +
                ", warnings=" + warnings +
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
