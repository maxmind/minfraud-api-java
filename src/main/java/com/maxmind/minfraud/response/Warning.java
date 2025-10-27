package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.JsonSerializable;

/**
 * This class represents a warning returned by the web service.
 *
 * @param code         This provides a machine-readable code identifying the warning. Although more
 *                     codes may be added in the future, the current codes are:
 *
 *                     <ul>
 *                     <li>BILLING_CITY_NOT_FOUND – the billing city could not be found in
 *                     our database.</li>
 *                     <li>BILLING_COUNTRY_NOT_FOUND – the billing country could not be
 *                     found in our database.</li>
 *                     <li>BILLING_POSTAL_NOT_FOUND – the billing postal could not be found
 *                     in our database.</li>
 *                     <li>INPUT_INVALID – the value associated with the key does not meet
 *                     the required constraints, e.g., "United States" in a field that
 *                     requires a two-letter country code.</li>
 *                     <li>INPUT_UNKNOWN – an unknown key was encountered in the request
 *                     body.</li>
 *                     <li>IP_ADDRESS_NOT_FOUND – the IP address could not be
 *                     geolocated.</li>
 *                     <li>SHIPPING_CITY_NOT_FOUND – the shipping city could not be found
 *                     in our database.</li>
 *                     <li>SHIPPING_COUNTRY_NOT_FOUND – the shipping country could not be
 *                     found in our database.</li>
 *                     <li>SHIPPING_POSTAL_NOT_FOUND – the shipping postal could not be
 *                     found in our database.</li>
 *                     </ul>
 * @param warning      This field provides a human-readable explanation of the warning. The
 *                     description may change at any time and should not be matched against.
 * @param inputPointer This is a JSON Pointer to the input that the warning is associated with. For
 *                     instance, if the warning was about the billing city, the value would be
 *                     "/billing/city". See
 *                     <a href="https://tools.ietf.org/html/rfc6901">RFC 6901</a> for the JSON
 *                     Pointer spec.
 */
public record Warning(
    @JsonProperty("code")
    String code,

    @JsonProperty("warning")
    String warning,

    @JsonProperty("input_pointer")
    String inputPointer
) implements JsonSerializable {

    /**
     * This provides a machine-readable code identifying the warning. Although more codes may be
     * added in the future, the current codes are:
     *
     * <ul>
     * <li>BILLING_CITY_NOT_FOUND – the billing city could not be found in
     * our database.</li>
     * <li>BILLING_COUNTRY_NOT_FOUND – the billing country could not be
     * found in our database.</li>
     * <li>BILLING_POSTAL_NOT_FOUND – the billing postal could not be found
     * in our database.</li>
     * <li>INPUT_INVALID – the value associated with the key does not meet
     * the required constraints, e.g., "United States" in a field that
     * requires a two-letter country code.</li>
     * <li>INPUT_UNKNOWN – an unknown key was encountered in the request
     * body.</li>
     * <li>IP_ADDRESS_NOT_FOUND – the IP address could not be
     * geolocated.</li>
     * <li>SHIPPING_CITY_NOT_FOUND – the shipping city could not be found
     * in our database.</li>
     * <li>SHIPPING_COUNTRY_NOT_FOUND – the shipping country could not be
     * found in our database.</li>
     * <li>SHIPPING_POSTAL_NOT_FOUND – the shipping postal could not be
     * found in our database.</li>
     * </ul>
     *
     * @return The warning code.
     * @deprecated Use {@link #code()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("code")
    public String getCode() {
        return code();
    }

    /**
     * @return This field provides a human-readable explanation of the warning. The description may
     *     change at any time and should not be matched against.
     * @deprecated Use {@link #warning()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("warning")
    public String getWarning() {
        return warning();
    }

    /**
     * @return This is a JSON Pointer to the input that the warning is associated with. For
     *     instance, if the warning was about the billing city, the value would be "/billing/city".
     *     See
     *     <a href="https://tools.ietf.org/html/rfc6901">RFC 6901</a> for the JSON Pointer spec.
     * @deprecated Use {@link #inputPointer()} instead. This method will be removed in 5.0.0.
     */
    @Deprecated(since = "4.0.0", forRemoval = true)
    @JsonProperty("input_pointer")
    public String getInputPointer() {
        return inputPointer();
    }
}
