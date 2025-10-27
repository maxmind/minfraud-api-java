package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * This class contains minFraud response data related to the device.
 * <p>
 * In order to receive device output from minFraud Insights or minFraud Factors, you must be using
 * the Device Tracking Add-on.
 *
 * @param confidence A number representing the confidence that the device ID refers to a unique
 *                   device as opposed to a cluster of similar devices. A confidence of 0.01
 *                   indicates very low confidence that the device is unique, whereas 99 indicates
 *                   very high confidence.
 * @param id         A UUID identifying the device associated with this IP address.
 * @param lastSeen   The date and time of the last sighting of the device. This is an RFC 3339
 *                   date-time.
 * @param localTime  The date and time of the transaction at the UTC offset associated with the
 *                   device. This is an RFC 3339 date-time.
 * @see <a href="https://dev.maxmind.com/minfraud/track-devices?lang=en">Device Tracking Add-on</a>
 */
public record Device(
    @JsonProperty("confidence")
    Double confidence,

    @JsonProperty("id")
    UUID id,

    @JsonProperty("last_seen")
    String lastSeen,

    @JsonProperty("local_time")
    String localTime
) implements JsonSerializable {

    /**
     * Constructs an instance of {@code Device} with no data.
     */
    public Device() {
        this(null, null, null, null);
    }

    /**
     * @return a number representing the confidence that the device ID refers to a unique device as
     *     opposed to a cluster of similar devices. A confidence of 0.01 indicates very low
     *     confidence that the device is unique, whereas 99 indicates very high confidence.
     * @deprecated Use {@link #confidence()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("confidence")
    public Double getConfidence() {
        return confidence();
    }

    /**
     * @return A UUID identifying the device associated with this IP address.
     * @deprecated Use {@link #id()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("id")
    public UUID getId() {
        return id();
    }

    /**
     * @return The date and time of the last sighting of the device. This is an RFC 3339 date-time.
     * @deprecated Use {@link #lastSeen()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("last_seen")
    public String getLastSeen() {
        return lastSeen();
    }

    /**
     * @return The date and time of the last sighting of the device as a {@code ZonedDateTime}.
     */
    @JsonIgnore
    public ZonedDateTime getLastSeenDateTime() {
        if (lastSeen == null) {
            return null;
        }
        return ZonedDateTime.parse(lastSeen);
    }

    /**
     * @return The date and time of the transaction at the UTC offset associated with the device.
     *     This is an RFC 3339 date-time.
     * @deprecated Use {@link #localTime()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("local_time")
    public String getLocalTime() {
        return localTime();
    }

    /**
     * @return The date and time of the transaction at the UTC offset associated with the device as
     *     a {@code ZonedDateTime}.
     */
    @JsonIgnore
    public ZonedDateTime getLocalDateTime() {
        if (localTime == null) {
            return null;
        }
        return ZonedDateTime.parse(localTime);
    }
}
