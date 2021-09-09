package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class contains the disposition set by custom rules.
 */
public final class Disposition extends AbstractModel {
    private final String action;
    private final String reason;
    private final String ruleLabel;

    public Disposition(
            @JsonProperty("action") String action,
            @JsonProperty("reason") String reason,
            @JsonProperty("rule_label") String ruleLabel
    ) {
        this.action = action;
        this.reason = reason;
        this.ruleLabel = ruleLabel;
    }

    public Disposition() {
        this(null, null, null);
    }

    /**
     * @return A {@code String} with the action to take on the transaction as
     * defined by your custom rules. The current set of values are
     * "accept", "manual_review", "reject" and "test". If you do not have
     * custom rules set up, {@code null} will be returned.
     */
    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    /**
     * @return A {@code String} with the reason for the action. The current
     * possible values are "custom_rule" and "default". If you do not have
     * custom rules set up, {@code null} will be returned.
     */
    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    /**
     * @return A {@code String} with the label of the custom rule that was
     * triggered. If you do not have custom rules set up, the triggered
     * custom rule does not have a label, or no custom rule was triggered,
     * {@code null} will be returned.
     */
    @JsonProperty("rule_label")
    public String getRuleLabel() {
        return ruleLabel;
    }
}
