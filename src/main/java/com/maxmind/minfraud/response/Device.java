package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.util.UUID;

/**
 * This class contains minFraud response data related to the device.
 */
public final class Device extends AbstractModel {
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
}
