package com.maxmind.minfraud.response;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a warning returned by the web service.
 */
public final class Warning {
    protected String code;
    protected String warning;
    protected List<String> input;

    /**
     * This provides a machine-readable code identifying the warning. Although
     * more codes may be added in the future, the current codes are:
     *
     * <ul>
     *     <li>BILLING_CITY_NOT_FOUND – the billing city could not be found in
     *     our database.</li>
     *     <li>BILLING_COUNTRY_NOT_FOUND – the billing country could not be
     *     found in our database.</li>
     *     <li>BILLING_POSTAL_NOT_FOUND – the billing postal could not be found
     *     in our database.</li>
     *     <li>INPUT_INVALID – the value associated with the key does not meet
     *     the required constraints, e.g., “United States” in a field that
     *     requires a two-letter country code.</li>
     *     <li>INPUT_UNKNOWN – an unknown key was encountered in the request
     *     body.</li>
     *     <li>IP_ADDRESS_NOT_FOUND – the IP address could not be
     *     geolocated.</li>
     *     <li>SHIPPING_CITY_NOT_FOUND – the shipping city could not be found
     *     in our database.</li>
     *     <li>SHIPPING_COUNTRY_NOT_FOUND – the shipping country could not be
     *     found in our database.</li>
     *     <li>SHIPPING_POSTAL_NOT_FOUND – the shipping postal could not be
     *     found in our database.</li>
     * </ul>
     *
     * @return The warning code.
     */
    public final String getCode() {
        return this.code;
    }

    /**
     * @return This field provides a human-readable explanation of the warning.
     * The description may change at any time and should not be matched against.
     */
    public final String getWarning() {
        return this.warning;
    }

    /**
     * @return This is a list of keys representing the path to the input that
     * the warning is associated with. For instance, if the warning was about
     * the billing city, the list would contain "billing" followed by "city".
     * The key is used for object and the index number for an array.
     */
    public final List<String> getInput() {
        return new ArrayList<>(this.input);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warning{");
        sb.append("code='").append(code).append('\'');
        sb.append(", warning='").append(warning).append('\'');
        sb.append(", input=").append(input);
        sb.append('}');
        return sb.toString();
    }
}
