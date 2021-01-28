package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class represents the reason for the IP risk.
 */
public final class IpRiskReason extends AbstractModel {
    private final String code;
    private final String reason;

    public IpRiskReason(
            @JsonProperty("code") String code,
            @JsonProperty("reason") String reason
    ) {
        this.code = code;
        this.reason = reason;
    }

    /**
     * This provides a machine-readable code identifying the reason. Although
     * more codes may be added in the future, the current codes are:
     * <dl>
     *     <dt>ANONYMOUS_IP</dt>
     *     <dd>The IP address belongs to an anonymous network. See the
     *     object at <code>.IPAddress.Traits</code> for more details.</dd>
     *
     *     <dt>BILLING_POSTAL_VELOCITY</dt>
     *     <dd>Many different billing postal codes have been seen on
     *     this IP address.</dd>
     *
     *     <dt>EMAIL_VELOCITY</dt>
     *     <dd>Many different email addresses have been seen on this
     *     IP address.</dd>
     *
     *     <dt>HIGH_RISK_DEVICE</dt>
     *     <dd>A high risk device was seen on this IP address.</dd>
     *
     *     <dt>HIGH_RISK_EMAIL</dt>
     *     <dd>A high risk email address was seen on this IP address in
     *     your past transactions.</dd>
     *
     *     <dt>ISSUER_ID_NUMBER_VELOCITY</dt>
     *     <dd>Many different issuer ID numbers have been seen on this
     *     IP address.</dd>
     *
     *     <dt>MINFRAUD_NETWORK_ACTIVITY</dt>
     *     <dd>Suspicious activity has been seen on this IP address
     *     across minFraud customers.</dd>
     * </dl>
     *
     * @return The reason code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return This field provides a human-readable explanation of the reason.
     * The description may change at any time and should not be matched against.
     */
    public String getReason() {
        return this.reason;
    }
}
