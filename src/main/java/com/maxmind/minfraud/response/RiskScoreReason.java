package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a risk score multiplier and reasons for that multiplier.
 */
public final class RiskScoreReason extends AbstractModel {
    private final Double multiplier;
    private final List<Reason> reasons;

    /**
     * Constructor for {@code RiskScoreReason}.
     *
     * @param multiplier The multiplier.
     * @param reasons    The reasons.
     */
    public RiskScoreReason(
        @JsonProperty("multiplier") Double multiplier,
        @JsonProperty("reasons") List<Reason> reasons
    ) {
        this.multiplier = multiplier;
        this.reasons =
            Collections.unmodifiableList(reasons == null ? List.of() : reasons);
    }

    /**
     * @return The factor by which the risk score is increased (if the value is greater than 1)
     *     or decreased (if the value is less than 1) for given risk reason(s).
     *     Multipliers greater than 1.5 and less than 0.66 are considered significant
     *     and lead to risk reason(s) being present.
     */
    @JsonProperty("multiplier")
    public Double getMultiplier() {
        return multiplier;
    }

    /**
     * @return An unmodifiable list containing objects that describe one of the reasons for
     *     the multiplier. This will be an empty list if there are no reasons.
     */
    @JsonProperty("reasons")
    public List<Reason> getReasons() {
        return reasons;
    }
}
