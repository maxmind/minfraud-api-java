package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.maxmind.minfraud.AbstractModel;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Custom inputs to be used in
 * <a href="https://www.maxmind.com/en/minfraud-interactive/#/custom-rules">Custom Rules</a>.
 * In order to use custom inputs, you must set them up from your account portal.
 */
public final class CustomInputs extends AbstractModel {
    private final Map<String, Object> inputs;

    private CustomInputs(Builder builder) {
        inputs = Map.copyOf(builder.inputs);
    }

    /**
     * {@code Builder} creates instances of {@code CustomInputs}
     * from values set by the builder's methods.
     */
    public static class Builder {
        private static final long NUM_MAX = 10_000_000_000_000L;
        private static final Pattern KEY_PATTERN = Pattern.compile("^[a-z0-9_]{1,25}$");

        final Map<String, Object> inputs = new HashMap<>();

        /**
         * Add a string custom input.
         *
         * @param key   The key for the custom input as defined on your account
         *              portal.
         * @param value The custom input value. Must be less than 256 characters
         *              and must not contain new lines.
         * @return The builder object.
         * @throws IllegalArgumentException when the key or value are invalid.
         */
        public Builder put(String key, String value) {
            validateKey(key);
            if (value.length() > 255 || value.contains("\n"))
                throw new IllegalArgumentException("The custom input string " +
                        value + " is invalid. The string be less than" +
                        "256 characters and the string must not contain a newline.");
            inputs.put(key, value);
            return this;
        }

        /**
         * Add a numeric custom input.
         *
         * @param key   The key for the custom input as defined on your account
         *              portal.
         * @param value The custom input value. Must be between -10^13 and 10^13
         *              exclusive.
         * @return The builder object.
         * @throws IllegalArgumentException when the key or value are invalid.
         */
        public Builder put(String key, Number value) {
            validateKey(key);
            double doubleValue = value.doubleValue();
            if (doubleValue <= -NUM_MAX || doubleValue >= NUM_MAX)
                throw new IllegalArgumentException(
                        "The custom input number " + value + "is invalid. " +
                                "The number must be between -" + NUM_MAX +
                                " and " + NUM_MAX + ", exclusive.");
            inputs.put(key, value);
            return this;
        }

        /**
         * Add a boolean custom input.
         *
         * @param key   The key for the custom input as defined on your account
         *              portal.
         * @param value The custom input value.
         * @return The builder object.
         * @throws IllegalArgumentException when the key or value are invalid.
         */
        public Builder put(String key, boolean value) {
            validateKey(key);
            inputs.put(key, value);
            return this;
        }

        /**
         * @return An instance of {@code CustomInputs} created from the
         * fields set on this builder.
         */
        public CustomInputs build() {
            return new CustomInputs(this);
        }


        private void validateKey(String key) {
            if (!KEY_PATTERN.matcher(key).matches()) {
                throw new IllegalArgumentException("The custom input key "
                        + key + " is invalid.");
            }
        }
    }

    /**
     * @return an unmodifiable map containing the custom inputs.
     */
    @JsonAnyGetter
    public Map<String, Object> getInputs() {
        return inputs;
    }
}
