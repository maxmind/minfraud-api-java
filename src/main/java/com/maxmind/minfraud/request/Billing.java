package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

/**
 * The billing information for the transaction.
 */
public final class Billing extends AbstractLocation {
    private final PhoneVerificationMethod phoneVerificationMethod;
    private final Boolean phoneWasVerificationSuccessful;
    private final ZonedDateTime phoneVerificationTime;

    private Billing(Billing.Builder builder) {
        super(builder);
        phoneVerificationMethod = builder.phoneVerificationMethod;
        phoneWasVerificationSuccessful = builder.phoneWasVerificationSuccessful;
        phoneVerificationTime = builder.phoneVerificationTime;
    }

    /**
     * {@code Builder} creates instances of {@code Billing} from values set by the builder's
     * methods.
     */
    public static final class Builder extends AbstractLocation.Builder<Billing.Builder> {
        PhoneVerificationMethod phoneVerificationMethod;
        Boolean phoneWasVerificationSuccessful;
        ZonedDateTime phoneVerificationTime;

        /**
         * @param method The most recent method used to verify the billing phone number.
         * @return The builder object.
         */
        public Billing.Builder phoneVerificationMethod(PhoneVerificationMethod method) {
            phoneVerificationMethod = method;
            return this;
        }

        /**
         * @param wasSuccessful Whether the most recent verification of the billing phone number
         *                      succeeded. Do not set this if no verification was attempted.
         * @return The builder object.
         */
        public Billing.Builder phoneWasVerificationSuccessful(Boolean wasSuccessful) {
            phoneWasVerificationSuccessful = wasSuccessful;
            return this;
        }

        /**
         * @param time The date and time of the most recent verification of the billing phone
         *             number.
         * @return The builder object.
         */
        public Billing.Builder phoneVerificationTime(ZonedDateTime time) {
            phoneVerificationTime = time;
            return this;
        }

        /**
         * @return An instance of {@code Billing} created from the fields set on this builder.
         */
        @Override
        public Billing build() {
            return new Billing(this);
        }
    }

    /**
     * @return The most recent method used to verify the billing phone number.
     */
    @JsonProperty("phone_verification_method")
    public PhoneVerificationMethod phoneVerificationMethod() {
        return phoneVerificationMethod;
    }

    /**
     * @return Whether the most recent verification of the billing phone number succeeded.
     */
    @JsonProperty("phone_was_verification_successful")
    public Boolean phoneWasVerificationSuccessful() {
        return phoneWasVerificationSuccessful;
    }

    /**
     * @return The date and time of the most recent verification of the billing phone number.
     */
    @JsonProperty("phone_verification_time")
    public ZonedDateTime phoneVerificationTime() {
        return phoneVerificationTime;
    }

    /**
     * Enumerated phone verification methods.
     */
    public enum PhoneVerificationMethod {
        /**
         * A code delivered to the phone, such as by SMS, voice call, or messaging app
         */
        DELIVERED_CODE,
        /**
         * Verification through the mobile network operator, such as silent network
         * authentication
         */
        NETWORK,
        /**
         * Another verification method
         */
        OTHER;

        /**
         * @return a string representation of the object.
         */
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
