package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

/**
 * The payment information for the transaction.
 */
public final class Payment extends AbstractModel {
    private final Processor processor;
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
    @JsonProperty("processor")
    public Processor getProcessor() {
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

    /**
     * Enumeration of payment processors
     */
    public enum Processor {
        // These should exactly match the mF2 request enum value except they
        // should be all uppercase.
        ADYEN,
        ALTAPAY,
        AMAZON_PAYMENTS,
        AMERICAN_EXPRESS_PAYMENT_GATEWAY,
        AUTHORIZENET,
        BALANCED,
        BEANSTREAM,
        BLUEPAY,
        BLUESNAP,
        BPOINT,
        BRAINTREE,
        CCAVENUE,
        CCNOW,
        CHASE_PAYMENTECH,
        CHECKOUT_COM,
        CIELO,
        COLLECTOR,
        COMMDOO,
        COMPROPAGO,
        CONCEPT_PAYMENTS,
        CONEKTA,
        CT_PAYMENTS,
        CUENTADIGITAL,
        CUROPAYMENTS,
        CYBERSOURCE,
        DALENYS,
        DALPAY,
        DATACASH,
        DIBS,
        DIGITAL_RIVER,
        EBS,
        ECOMM365,
        ELAVON,
        EMERCHANTPAY,
        EPAY,
        EPROCESSING_NETWORK,
        EWAY,
        EXACT,
        FIRST_DATA,
        GLOBAL_PAYMENTS,
        GOCARDLESS,
        HEARTLAND,
        HIPAY,
        INGENICO,
        INTERNETSECURE,
        INTUIT_QUICKBOOKS_PAYMENTS,
        IUGU,
        LEMON_WAY,
        MASTERCARD_PAYMENT_GATEWAY,
        MERCADOPAGO,
        MERCHANT_ESOLUTIONS,
        MIRJEH,
        MOLLIE,
        MONERIS_SOLUTIONS,
        NMI,
        OCEANPAYMENT,
        ONEY,
        OPENPAYMX,
        OPTIMAL_PAYMENTS,
        ORANGEPAY,
        OTHER,
        PACNET_SERVICES,
        PAYEEZY,
        PAYFAST,
        PAYGATE,
        PAYLIKE,
        PAYMENT_EXPRESS,
        PAYMENTWALL,
        PAYONE,
        PAYPAL,
        PAYPLUS,
        PAYSTATION,
        PAYTRACE,
        PAYTRAIL,
        PAYTURE,
        PAYU,
        PAYULATAM,
        PAYWAY,
        PAYZA,
        PINPAYMENTS,
        POSCONNECT,
        PRINCETON_PAYMENT_SOLUTIONS,
        PSIGATE,
        QIWI,
        QUICKPAY,
        RABERIL,
        REDE,
        REDPAGOS,
        REWARDSPAY,
        SAGEPAY,
        SECURETRADING,
        SIMPLIFY_COMMERCE,
        SKRILL,
        SMARTCOIN,
        SMARTDEBIT,
        SOLIDTRUST_PAY,
        SPS_DECIDIR,
        STRIPE,
        SYNAPSEFI,
        TELERECARGAS,
        TOWAH,
        TRANSACT_PRO,
        USA_EPAY,
        VANTIV,
        VERAPAY,
        VERICHECK,
        VINDICIA,
        VIRTUAL_CARD_SERVICES,
        VME,
        VPOS,
        WIRECARD,
        WORLDPAY;

        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
