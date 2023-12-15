package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * The payment information for the transaction.
 */
public final class Payment extends AbstractModel {
    private final PaymentProcessorsList processor;
    private final Boolean wasAuthorized;
    private final String declineCode;

    private Payment(Payment.Builder builder) {
        processor = builder.processor;
        wasAuthorized = builder.wasAuthorized;
        declineCode = builder.declineCode;
    }

    /**
     * {@code Builder} creates instances of {@code Payment}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        PaymentProcessorsList processor;
        Boolean wasAuthorized;
        String declineCode;

        /**
         * @param processor The payment processor used for the transaction.
         * @return The builder object.
         */
        public Payment.Builder processor(PaymentProcessorsList processor) {
            this.processor = processor;
            return this;
        }

        /**
         * @param wasAuthorized The authorization outcome from the payment
         *                      processor. If the transaction has not yet been
         *                      approved or denied, do not include this field.
         * @return The builder object.
         */
        public Payment.Builder wasAuthorized(boolean wasAuthorized) {
            this.wasAuthorized = wasAuthorized;
            return this;
        }

        /**
         * @param declineCode The decline code as provided by your payment
         *                    processor. If the transaction was not declined,
         *                    do not include this field.
         * @return The builder object.
         */
        public Payment.Builder declineCode(String declineCode) {
            this.declineCode = declineCode;
            return this;
        }

        /**
         * @return an instance of {@code Payment} created from the
         * fields set on this builder.
         */
        public Payment build() {
            return new Payment(this);
        }
    }

    /**
     * @return The payment processor.
     */
    @JsonProperty("processor")
    public PaymentProcessorsList getProcessor() {
        return processor;
    }

    /**
     * @return The authorization outcome.
     */
    @JsonProperty("was_authorized")
    public Boolean wasAuthorized() {
        return wasAuthorized;
    }

    /**
     * @return The decline code.
     */
    @JsonProperty("decline_code")
    public String getDeclineCode() {
        return declineCode;
    }
}

