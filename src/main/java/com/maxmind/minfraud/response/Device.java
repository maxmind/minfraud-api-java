package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * This class contains minFraud response data related to the device.
 */
public final class Device {
    private final UUID id;

    public Device(
            @JsonProperty("id") UUID id
    ) {
        this.id = id;
    }

    public Device() {
        this(null);
    }

    /**
     * @return The device id.
     */
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Device{");
        sb.append("id=").append(this.id);
        sb.append('}');
        return sb.toString();
    }
}
