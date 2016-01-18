package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains minFraud response data related to the device.
 */
public final class Device {
    private final String id;

    public Device(
            @JsonProperty("id") String id
    ) {
        this.id = id;
    }

    public Device() {
        this(null);
    }

    /**
     * @return The device id.
     */
    public String getId() {
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
