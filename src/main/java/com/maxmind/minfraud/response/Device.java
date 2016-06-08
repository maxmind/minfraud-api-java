package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.util.UUID;

/**
 * This class contains minFraud response data related to the device.
 *
 *  In order to receive device output from minFraud Insights or minFraud
 *  Factors, you must be using the Device Tracking Add-on.
 *
 *  @see <a href="https://dev.maxmind.com/minfraud/device/">Device Tracking Add-on</a>

 */
public final class Device extends AbstractModel {
    private final Double confidence;
    private final UUID id;
    private final String lastSeen;

    public Device(
            @JsonProperty("confidence") Double confidence,
            @JsonProperty("id") UUID id,
            @JsonProperty("last_seen") String lastSeen
    ) {
        this.confidence = confidence;
        this.id = id;
        this.lastSeen = lastSeen;
    }

    public Device() {
        this(null, null, null);
    }

    /**
     * @return a number representing the confidence that the device ID refers
     * to a unique device as opposed to a cluster of similar devices. A
     * confidence of 0.01 indicates very low confidence that the device is
     * unique, whereas 99 indicates very high confidence.
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * @return A UUID identifying the device associated with this IP address.
     * Note that many devices cannot be uniquely identified because they are too
     * common (for example, all iPhones of a given model and OS release).
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return The date and time of the last sighting of the device. This is an
     * RFC 3339 date-time.
     */
    @JsonProperty("last_seen")
    public String getLastSeen() {
        return lastSeen;
    }
}
