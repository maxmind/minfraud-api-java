package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The payment information for the transaction.
 */
public final class Payment {
    @JsonProperty("processor")
    private final Processor processor;
    @JsonProperty("was_authorized")
    private final Boolean wasAuthorized;
    @JsonProperty("decline_code")
    private final String declineCode;

    public Payment(Payment.Builder builder) {
        processor = builder.processor;
        wasAuthorized = builder.wasAuthorized;
        declineCode = builder.declineCode;
    }

    /**
     * {@code Builder} creates instances of {@code Payment}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        Processor processor;
        Boolean wasAuthorized;
        String declineCode;

        /**
         * @param processor The payment processor used for the transaction.
         * @return The builder object.
         */
        public Payment.Builder processor(Processor processor) {
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
    public final Processor getProcessor() {
        return processor;
    }

    /**
     * @return The authorization outcome.
     */
    public final Boolean wasAuthorized() {
        return wasAuthorized;
    }

    /**
     * @return The decline code.
     */
    public final String getDeclineCode() {
        return declineCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Payment{");
        sb.append("processor=").append(this.processor);
        sb.append(", wasAuthorized=").append(this.wasAuthorized);
        sb.append(", declineCode='").append(this.declineCode).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Enumeration of payment processors
     */
    public enum Processor {
        ADYEN,
        ALTAPAY,
        AMAZON_PAYMENTS,
        AUTHORIZENET,
        BALANCED,
        BEANSTREAM,
        BLUEPAY,
        BRAINTREE,
        CHASE_PAYMENTECH,
        CIELO,
        COLLECTOR,
        COMPROPAGO,
        CONEKTA,
        CUENTADIGITAL,
        DIBS,
        DIGITAL_RIVER,
        ELAVON,
        EPAYEU,
        EPROCESSING_NETWORK,
        EWAY,
        FIRST_DATA,
        GLOBAL_PAYMENTS,
        INGENICO,
        INTERNETSECURE,
        INTUIT_QUICKBOOKS_PAYMENTS,
        IUGU,
        MASTERCARD_PAYMENT_GATEWAY,
        MERCADOPAGO,
        MERCHANT_ESOLUTIONS,
        MIRJEH,
        MOLLIE,
        MONERIS_SOLUTIONS,
        NMI,
        OTHER,
        OPENPAYMX,
        OPTIMAL_PAYMENTS,
        PAYFAST,
        PAYGATE,
        PAYONE,
        PAYPAL,
        PAYSTATION,
        PAYTRACE,
        PAYTRAIL,
        PAYTURE,
        PAYU,
        PAYULATAM,
        PRINCETON_PAYMENT_SOLUTIONS,
        PSIGATE,
        QIWI,
        RABERIL,
        REDE,
        REDPAGOS,
        REWARDSPAY,
        SAGEPAY,
        SIMPLIFY_COMMERCE,
        SKRILL,
        SMARTCOIN,
        SPS_DECIDIR,
        STRIPE,
        TELERECARGAS,
        TOWAH,
        USA_EPAY,
        VINDICIA,
        VIRTUAL_CARD_SERVICES,
        VME,
        WORLDPAY;

        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
