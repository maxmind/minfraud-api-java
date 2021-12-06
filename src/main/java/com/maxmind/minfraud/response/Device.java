package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * This class contains minFraud response data related to the device.
 * <p>
 * In order to receive device output from minFraud Insights or minFraud
 * Factors, you must be using the Device Tracking Add-on.
 *
 * @see <a href="https://dev.maxmind.com/minfraud/track-devices?lang=en">Device Tracking Add-on</a>
 */
public final class Device extends AbstractModel {
    private final Double confidence;
    private final UUID id;
    private final String lastSeen;
    private final String localTime;

    /**
     * @param confidence The device confidence.
     * @param id         The device ID.
     * @param lastSeen   When the device was last seen.
     * @param localTime  The local time of the device.
     */
    public Device(
            @JsonProperty("confidence") Double confidence,
            @JsonProperty("id") UUID id,
            @JsonProperty("last_seen") String lastSeen,
            @JsonProperty("local_time") String localTime
    ) {
        this.confidence = confidence;
        this.id = id;
        this.lastSeen = lastSeen;
        this.localTime = localTime;
    }

    public Device() {
        this(null, null, null, null);
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

    /**
     * @return The date and time of the last sighting of the device as a
     * {@code ZonedDateTime}.
     */
    @JsonIgnore
    public ZonedDateTime getLastSeenDateTime() {
        if (lastSeen == null) {
            return null;
        }
        return ZonedDateTime.parse(lastSeen);
    }

    /**
     * @return The date and time of the transaction at the UTC offset
     * associated with the device. This is an RFC 3339 date-time.
     */
    @JsonProperty("local_time")
    public String getLocalTime() {
        return localTime;
    }

    /**
     * @return The date and time of the transaction at the UTC offset
     * associated with the device as a {@code ZonedDateTime}.
     */
    @JsonIgnore
    public ZonedDateTime getLocalDateTime() {
        if (localTime == null) {
            return null;
        }
        return ZonedDateTime.parse(localTime);
    }
}
