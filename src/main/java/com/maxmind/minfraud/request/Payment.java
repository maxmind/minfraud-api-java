package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The payment information for the transaction.
 */
public class Payment {
    @JsonProperty("processor")
    private final Processor processor;
    @JsonProperty("was_authorized")
    private final boolean wasAuthorized;
    @JsonProperty("decline_code")
    private final String declineCode;

    public Payment(Payment.Builder builder) {
        processor = builder.processor;
        wasAuthorized = builder.wasAuthorized;
        declineCode = builder.declineCode;
    }

    public static final class Builder {
        Processor processor;
        boolean wasAuthorized;
        String declineCode;

        public Payment.Builder processor(Processor processor) {
            this.processor = processor;
            return this;
        }

        public Payment.Builder wasAuthorized(boolean wasAuthorized) {
            this.wasAuthorized = wasAuthorized;
            return this;
        }

        public Payment.Builder declineCode(String declineCode) {
            this.declineCode = declineCode;
            return this;
        }


        public Payment build() {
            return new Payment(this);
        }
    }

    public final Processor getProcessor() {
        return processor;
    }

    public final boolean wasAuthorized() {
        return wasAuthorized;
    }

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
