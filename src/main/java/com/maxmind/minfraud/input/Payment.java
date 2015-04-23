package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The payment information for the transaction.
 */
public class Payment {
    @JsonProperty("processor")
    private final Payment.Processor processor;
    @JsonProperty("was_authorized")
    private final boolean wasAuthorized;
    @JsonProperty("decline_code")
    private final String declineCode;

    public Payment(Builder builder) {
        this.processor = builder.processor;
        this.wasAuthorized = builder.wasAuthorized;
        this.declineCode = builder.declineCode;
    }

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
            return name().toLowerCase();
        }
    }

    public Payment.Processor getProcessor() {
        return this.processor;
    }

    public boolean isWasAuthorized() {
        return this.wasAuthorized;
    }

    public String getDeclineCode() {
        return this.declineCode;
    }

    public final static class Builder {
        Payment.Processor processor;
        boolean wasAuthorized;
        String declineCode;

        public Builder processor(Payment.Processor processor) {
            this.processor = processor;
            return this;
        }

        public Builder wasAuthorized(boolean wasAuthorized) {
            this.wasAuthorized = wasAuthorized;
            return this;
        }

        public Builder declineCode(String declineCode) {
            this.declineCode = declineCode;
            return this;
        }


        public Payment build() {
            return new Payment(this);
        }
    }
}
