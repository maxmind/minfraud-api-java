package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class contains the disposition set by custom rules.
 *
 * @param action    A {@code String} with the action to take on the transaction as defined by your
 *                  custom rules. The current set of values are "accept", "manual_review", "reject"
 *                  and "test". If you do not have custom rules set up, {@code null} will be
 *                  returned.
 * @param reason    A {@code String} with the reason for the action. The current possible values
 *                  are "custom_rule" and "default". If you do not have custom rules set up,
 *                  {@code null} will be returned.
 * @param ruleLabel A {@code String} with the label of the custom rule that was triggered. If you
 *                  do not have custom rules set up, the triggered custom rule does not have a
 *                  label, or no custom rule was triggered, {@code null} will be returned.
 */
public record Disposition(
    @JsonProperty("action")
    String action,

    @JsonProperty("reason")
    String reason,

    @JsonProperty("rule_label")
    String ruleLabel
) implements JsonSerializable {

    /**
     * Constructs an instance of {@code Disposition} with no data.
     */
    public Disposition() {
        this(null, null, null);
    }

    /**
     * @return A {@code String} with the action to take on the transaction as defined by your custom
     *     rules. The current set of values are "accept", "manual_review", "reject" and "test". If
     *     you do not have custom rules set up, {@code null} will be returned.
     * @deprecated Use {@link #action()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("action")
    public String getAction() {
        return action();
    }

    /**
     * @return A {@code String} with the reason for the action. The current possible values are
     *     "custom_rule" and "default". If you do not have custom rules set up, {@code null} will be
     *     returned.
     * @deprecated Use {@link #reason()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("reason")
    public String getReason() {
        return reason();
    }

    /**
     * @return A {@code String} with the label of the custom rule that was triggered. If you do not
     *     have custom rules set up, the triggered custom rule does not have a label, or no custom
     *     rule was triggered, {@code null} will be returned.
     * @deprecated Use {@link #ruleLabel()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("rule_label")
    public String getRuleLabel() {
        return ruleLabel();
    }
}
