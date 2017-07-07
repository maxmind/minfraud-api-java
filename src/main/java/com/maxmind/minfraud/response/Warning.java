package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * This class represents a warning returned by the web service.
 */
public final class Warning extends AbstractModel {
    private final String code;
    private final String warning;
    private final String inputPointer;

    public Warning(
            @JsonProperty("code") String code,
            @JsonProperty("warning") String warning,
            @JsonProperty("input_pointer") String inputPointer
    ) {
        this.code = code;
        this.warning = warning;
        this.inputPointer = inputPointer;
    }

    /**
     * This provides a machine-readable code identifying the warning. Although
     * more codes may be added in the future, the current codes are:
     *
     * <ul>
     * <li>BILLING_CITY_NOT_FOUND – the billing city could not be found in
     * our database.</li>
     * <li>BILLING_COUNTRY_NOT_FOUND – the billing country could not be
     * found in our database.</li>
     * <li>BILLING_POSTAL_NOT_FOUND – the billing postal could not be found
     * in our database.</li>
     * <li>INPUT_INVALID – the value associated with the key does not meet
     * the required constraints, e.g., “United States” in a field that
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
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return This field provides a human-readable explanation of the warning.
     * The description may change at any time and should not be matched against.
     */
    public String getWarning() {
        return this.warning;
    }

    /**
     * @return This is a JSON Pointer to the input that the warning is
     * associated with. For instance, if the warning was about the billing
     * city, the value would be "/billing/city". See
     * https://tools.ietf.org/html/rfc6901 for the JSON Pointer spec.
     */
    @JsonProperty("input_pointer")
    public String getInputPointer() {
        return this.inputPointer;
    }
}
