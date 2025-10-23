package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.util.List;

/**
 * This class represents a risk score multiplier and reasons for that multiplier.
 *
 * @param multiplier The factor by which the risk score is increased (if the value is greater than
 *                   1) or decreased (if the value is less than 1) for given risk reason(s).
 *                   Multipliers greater than 1.5 and less than 0.66 are considered significant and
 *                   lead to risk reason(s) being present.
 * @param reasons    An unmodifiable list containing objects that describe one of the reasons for
 *                   the multiplier. This will be an empty list if there are no reasons.
 */
public record RiskScoreReason(
    @JsonProperty("multiplier")
    Double multiplier,

    @JsonProperty("reasons")
    List<Reason> reasons
) implements JsonSerializable {

    /**
     * Compact canonical constructor that ensures immutability.
     */
    public RiskScoreReason {
        reasons = reasons != null ? List.copyOf(reasons) : List.of();
    }

    /**
     * @return The factor by which the risk score is increased (if the value is greater than 1)
     *     or decreased (if the value is less than 1) for given risk reason(s).
     *     Multipliers greater than 1.5 and less than 0.66 are considered significant
     *     and lead to risk reason(s) being present.
     * @deprecated Use {@link #multiplier()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("multiplier")
    public Double getMultiplier() {
        return multiplier();
    }

    /**
     * @return An unmodifiable list containing objects that describe one of the reasons for
     *     the multiplier. This will be an empty list if there are no reasons.
     * @deprecated Use {@link #reasons()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("reasons")
    public List<Reason> getReasons() {
        return reasons();
    }
}
