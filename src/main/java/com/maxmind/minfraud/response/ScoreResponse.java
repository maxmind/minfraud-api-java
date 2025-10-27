package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the minFraud Score response.
 *
 * @param disposition      The disposition set by your custom rules.
 * @param fundsRemaining   The approximate US dollar value of the funds remaining on your MaxMind
 *                         account.
 * @param id               This is a UUID that identifies the minFraud request.
 * @param ipAddress        The {@code IpAddress} model object.
 * @param queriesRemaining The approximate number of queries remaining for this service before your
 *                         account runs out of funds.
 * @param riskScore        This returns the risk score, from 0.01 to 99. A higher score indicates
 *                         a higher risk of fraud. For example, a score of 20 indicates a 20%
 *                         chance that a transaction is fraudulent. We never return a risk score of
 *                         0, since all transactions have the possibility of being fraudulent.
 *                         Likewise, we never return a risk score of 100.
 * @param warnings         An unmodifiable list containing warning objects that detail issues with
 *                         the request such as invalid or unknown inputs. It is highly recommended
 *                         that you check this list for issues when integrating the web service.
 */
public record ScoreResponse(
    @JsonProperty("disposition")
    Disposition disposition,

    @JsonProperty("funds_remaining")
    Double fundsRemaining,

    @JsonProperty("id")
    UUID id,

    @JsonProperty("ip_address")
    ScoreIpAddress ipAddress,

    @JsonProperty("queries_remaining")
    Integer queriesRemaining,

    @JsonProperty("risk_score")
    Double riskScore,

    @JsonProperty("warnings")
    List<Warning> warnings
) implements JsonSerializable {

    /**
     * Compact canonical constructor that sets defaults for null values.
     */
    public ScoreResponse {
        disposition = disposition != null ? disposition : new Disposition();
        ipAddress = ipAddress != null ? ipAddress : new ScoreIpAddress();
        warnings = warnings != null ? List.copyOf(warnings) : List.of();
    }

    /**
     * Constructs an instance of {@code ScoreResponse} with no data.
     */
    public ScoreResponse() {
        this(null, null, null, null, null, null, null);
    }

    /**
     * @return The disposition set by your custom rules.
     * @deprecated Use {@link #disposition()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("disposition")
    public Disposition getDisposition() {
        return disposition();
    }

    /**
     * @return The approximate US dollar value of the funds remaining on your MaxMind account.
     * @deprecated Use {@link #fundsRemaining()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("funds_remaining")
    public Double getFundsRemaining() {
        return fundsRemaining();
    }

    /**
     * @return This is a UUID that identifies the minFraud request.
     * @deprecated Use {@link #id()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("id")
    public UUID getId() {
        return id();
    }

    /**
     * @return The {@code IpAddress} model object.
     * @deprecated Use {@link #ipAddress()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("ip_address")
    public IpAddressInterface getIpAddress() {
        return ipAddress();
    }

    /**
     * @return The approximate number of queries remaining for this service before your account runs
     *     out of funds.
     * @deprecated Use {@link #queriesRemaining()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("queries_remaining")
    public Integer getQueriesRemaining() {
        return queriesRemaining();
    }

    /**
     * @return This returns the risk score, from 0.01 to 99. A higher score indicates a higher risk
     *     of fraud. For example, a score of 20 indicates a 20% chance that a transaction is
     *     fraudulent. We never return a risk score of 0, since all transactions have the
     *     possibility of being fraudulent. Likewise, we never return a risk score of 100.
     * @deprecated Use {@link #riskScore()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("risk_score")
    public Double getRiskScore() {
        return riskScore();
    }

    /**
     * @return An unmodifiable list containing warning objects that detail issues with the request
     *     such as invalid or unknown inputs. It is highly recommended that you check this list for
     *     issues when integrating the web service.
     * @deprecated Use {@link #warnings()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("warnings")
    public List<Warning> getWarnings() {
        return warnings();
    }
}
