package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains the IP address risk.
 */
public final class ScoreIpAddress implements IpAddressInterface {
    private final Double risk;

    public ScoreIpAddress(
            @JsonProperty("risk") Double risk
    ) {
        this.risk = risk;
    }

    public ScoreIpAddress() {
        this(null);
    }

    /**
     * @return The risk associated with the IP address. The value ranges from
     * 0.01 to 99. A higher score indicates a higher risk.
     */
    public Double getRisk() {
        return risk;
    }
}
