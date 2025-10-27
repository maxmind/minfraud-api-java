package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class contains the IP address risk.
 *
 * @param risk The risk associated with the IP address.
 */
public record ScoreIpAddress(
    @JsonProperty("risk")
    Double risk
) implements IpAddressInterface, JsonSerializable {

    /**
     * Constructs an instance of {@code ScoreIpAddress} with no data.
     */
    public ScoreIpAddress() {
        this(null);
    }

    /**
     * @return The risk associated with the IP address.
     * @deprecated Use {@link #risk()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("risk")
    public Double getRisk() {
        return risk();
    }
}
