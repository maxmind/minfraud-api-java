package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class contains the disposition set by custom rules.
 */
public final class Disposition extends AbstractModel {
    private final String action;
    private final String reason;

    public Disposition(
            @JsonProperty("action") String action,
            @JsonProperty("reason") String reason
    ) {
        this.action = action;
        this.reason = reason;
    }

    public Disposition() {
        this(null, null);
    }

    /**
     * @return A {@code String} with the action to take on the transaction as
     * defined by your custom rules. The current set of values are
     * "accept", "manual_review", and "reject". If you do not have
     * custom rules set up, {@code null} will be returned.
     */
    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    /**
     * @return A {@code String} with the reason for the action. The current
     * possible values are "custom_rule", "block_list", and "default".
     * If you do not have custom rules set up, {@code null} will be
     * returned.
     */
    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }
}
