package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
