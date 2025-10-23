package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class represents a risk score reason for the multiplier.
 *
 * @param code   This field provides a machine-readable code identifying the reason.
 *               Although more <a href="https://dev.maxmind.com/minfraud/api-documentation/responses/#schema--response--risk-score-reason--multiplier-reason">codes</a>
 *               may be added in the future, the current codes are:
 *
 *               <ul>
 *               <li>BROWSER_LANGUAGE - Riskiness of the browser user-agent
 *                   and language associated with the request.</li>
 *               <li>BUSINESS_ACTIVITY - Riskiness of business activity associated with the
 *                   request.</li>
 *               <li>COUNTRY - Riskiness of the country associated with the request.</li>
 *               <li>CUSTOMER_ID - Riskiness of a customer's activity.</li>
 *               <li>EMAIL_DOMAIN - Riskiness of email domain.</li>
 *               <li>EMAIL_DOMAIN_NEW - Riskiness of newly-sighted email domain.</li>
 *               <li>EMAIL_ADDRESS_NEW - Riskiness of newly-sighted email address.</li>
 *               <li>EMAIL_LOCAL_PART - Riskiness of the local part of the email address.</li>
 *               <li>EMAIL_VELOCITY - Velocity on email - many requests on same email
 *                   over short period of time.</li>
 *               <li>ISSUER_ID_NUMBER_COUNTRY_MISMATCH - Riskiness of the country mismatch
 *                   between IP, billing, shipping and IIN country.</li>
 *               <li>ISSUER_ID_NUMBER_ON_SHOP_ID - Risk of Issuer ID Number for the shop ID.</li>
 *               <li>ISSUER_ID_NUMBER_LAST_DIGITS_ACTIVITY - Riskiness of many recent
 *                   requests and previous high-risk requests on the IIN and last digits of the
 *                   credit card.</li>
 *               <li>ISSUER_ID_NUMBER_SHOP_ID_VELOCITY - Risk of recent Issuer ID Number activity
 *                   for the shop ID.</li>
 *               <li>INTRACOUNTRY_DISTANCE - Risk of distance between IP, billing,
 *                   and shipping location.</li>
 *               <li>ANONYMOUS_IP - Risk due to IP being an Anonymous IP.</li>
 *               <li>IP_BILLING_POSTAL_VELOCITY - Velocity of distinct billing postal code
 *                   on IP address.</li>
 *               <li>IP_EMAIL_VELOCITY - Velocity of distinct email address on IP address.</li>
 *               <li>IP_HIGH_RISK_DEVICE - High-risk device sighted on IP address.</li>
 *               <li>IP_ISSUER_ID_NUMBER_VELOCITY - Velocity of distinct IIN on IP address.</li>
 *               <li>IP_ACTIVITY - Riskiness of IP based on minFraud network activity.</li>
 *               <li>LANGUAGE - Riskiness of browser language.</li>
 *               <li>MAX_RECENT_EMAIL - Riskiness of email address based on
 *                   past minFraud risk scores on email.</li>
 *               <li>MAX_RECENT_PHONE - Riskiness of phone number based on
 *                   past minFraud risk scores on phone.</li>
 *               <li>MAX_RECENT_SHIP - Riskiness of email address based on
 *                   past minFraud risk scores on ship address.</li>
 *               <li>MULTIPLE_CUSTOMER_ID_ON_EMAIL - Riskiness of email address
 *                   having many customer IDs.</li>
 *               <li>ORDER_AMOUNT - Riskiness of the order amount.</li>
 *               <li>ORG_DISTANCE_RISK - Risk of ISP and distance between billing address
 *                   and IP location.</li>
 *               <li>PHONE - Riskiness of the phone number or related numbers.</li>
 *               <li>CART - Riskiness of shopping cart contents.</li>
 *               <li>TIME_OF_DAY - Risk due to local time of day.</li>
 *               <li>TRANSACTION_REPORT_EMAIL - Risk due to transaction reports on the email
 *                   address.</li>
 *               <li>TRANSACTION_REPORT_IP - Risk due to transaction reports on the IP
 *                   address.</li>
 *               <li>TRANSACTION_REPORT_PHONE - Risk due to transaction reports on the phone
 *                   number.</li>
 *               <li>TRANSACTION_REPORT_SHIP - Risk due to transaction reports on the shipping
 *                   address.</li>
 *               <li>EMAIL_ACTIVITY - Riskiness of the email address based on minFraud network
 *                   activity.</li>
 *               <li>PHONE_ACTIVITY - Riskiness of the phone number based on minFraud network
 *                   activity.</li>
 *               <li>SHIP_ACTIVITY - Riskiness of ship address based on minFraud network
 *                   activity.</li>
 *               </ul>
 * @param reason The human-readable explanation of the reason. The description may change at any
 *               time and should not be matched against.
 */
public record Reason(
    @JsonProperty("code")
    String code,

    @JsonProperty("reason")
    String reason
) implements JsonSerializable {

    /**
     * This field provides a machine-readable code identifying the reason.
     * Although more <a href="https://dev.maxmind.com/minfraud/api-documentation/responses/#schema--response--risk-score-reason--multiplier-reason">codes</a>
     * may be added in the future, the current codes are:
     *
     * <ul>
     * <li>BROWSER_LANGUAGE - Riskiness of the browser user-agent
     *     and language associated with the request.</li>
     * <li>BUSINESS_ACTIVITY - Riskiness of business activity associated with the request.</li>
     * <li>COUNTRY - Riskiness of the country associated with the request.</li>
     * <li>CUSTOMER_ID - Riskiness of a customer's activity.</li>
     * <li>EMAIL_DOMAIN - Riskiness of email domain.</li>
     * <li>EMAIL_DOMAIN_NEW - Riskiness of newly-sighted email domain.</li>
     * <li>EMAIL_ADDRESS_NEW - Riskiness of newly-sighted email address.</li>
     * <li>EMAIL_LOCAL_PART - Riskiness of the local part of the email address.</li>
     * <li>EMAIL_VELOCITY - Velocity on email - many requests on same email
     *     over short period of time.</li>
     * <li>ISSUER_ID_NUMBER_COUNTRY_MISMATCH - Riskiness of the country mismatch between IP,
     *     billing, shipping and IIN country.</li>
     * <li>ISSUER_ID_NUMBER_ON_SHOP_ID - Risk of Issuer ID Number for the shop ID.</li>
     * <li>ISSUER_ID_NUMBER_LAST_DIGITS_ACTIVITY - Riskiness of many recent requests
     *     and previous high-risk requests on the IIN and last digits of the credit card.</li>
     * <li>ISSUER_ID_NUMBER_SHOP_ID_VELOCITY - Risk of recent Issuer ID Number activity
     *     for the shop ID.</li>
     * <li>INTRACOUNTRY_DISTANCE - Risk of distance between IP, billing,
     *     and shipping location.</li>
     * <li>ANONYMOUS_IP - Risk due to IP being an Anonymous IP.</li>
     * <li>IP_BILLING_POSTAL_VELOCITY - Velocity of distinct billing postal code
     *     on IP address.</li>
     * <li>IP_EMAIL_VELOCITY - Velocity of distinct email address on IP address.</li>
     * <li>IP_HIGH_RISK_DEVICE - High-risk device sighted on IP address.</li>
     * <li>IP_ISSUER_ID_NUMBER_VELOCITY - Velocity of distinct IIN on IP address.</li>
     * <li>IP_ACTIVITY - Riskiness of IP based on minFraud network activity.</li>
     * <li>LANGUAGE - Riskiness of browser language.</li>
     * <li>MAX_RECENT_EMAIL - Riskiness of email address based on
     *     past minFraud risk scores on email.</li>
     * <li>MAX_RECENT_PHONE - Riskiness of phone number based on
     *     past minFraud risk scores on phone.</li>
     * <li>MAX_RECENT_SHIP - Riskiness of email address based on
     *     past minFraud risk scores on ship address.</li>
     * <li>MULTIPLE_CUSTOMER_ID_ON_EMAIL - Riskiness of email address
     *     having many customer IDs.</li>
     * <li>ORDER_AMOUNT - Riskiness of the order amount.</li>
     * <li>ORG_DISTANCE_RISK - Risk of ISP and distance between billing address
     *     and IP location.</li>
     * <li>PHONE - Riskiness of the phone number or related numbers.</li>
     * <li>CART - Riskiness of shopping cart contents.</li>
     * <li>TIME_OF_DAY - Risk due to local time of day.</li>
     * <li>TRANSACTION_REPORT_EMAIL - Risk due to transaction reports on the email address.</li>
     * <li>TRANSACTION_REPORT_IP - Risk due to transaction reports on the IP address.</li>
     * <li>TRANSACTION_REPORT_PHONE - Risk due to transaction reports on the phone number.</li>
     * <li>TRANSACTION_REPORT_SHIP - Risk due to transaction reports on the shipping address.</li>
     * <li>EMAIL_ACTIVITY - Riskiness of the email address based on minFraud network activity.</li>
     * <li>PHONE_ACTIVITY - Riskiness of the phone number based on minFraud network activity.</li>
     * <li>SHIP_ACTIVITY - Riskiness of ship address based on minFraud network activity.</li>
     * </ul>
     *
     * @return The code.
     * @deprecated Use {@link #code()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("code")
    public String getCode() {
        return code();
    }

    /**
     * @return The human-readable explanation of the reason. The description may change at any time
     *     and should not be matched against.
     * @deprecated Use {@link #reason()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("reason")
    public String getReason() {
        return reason();
    }
}
