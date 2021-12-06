package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.net.InetAddress;
import java.util.UUID;

/**
 * The transaction information to report.
 */
public final class TransactionReport extends AbstractModel {
    private final InetAddress ipAddress;
    private final Tag tag;
    private final String chargebackCode;
    private final String maxmindId;
    private final UUID minfraudId;
    private final String notes;
    private final String transactionId;

    private TransactionReport(TransactionReport.Builder builder) {
        ipAddress = builder.ipAddress;
        tag = builder.tag;
        chargebackCode = builder.chargebackCode;
        maxmindId = builder.maxmindId;
        minfraudId = builder.minfraudId;
        notes = builder.notes;
        transactionId = builder.transactionId;
    }

    /**
     * {@code Builder} creates instances of {@code TransactionReport}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        InetAddress ipAddress;
        Tag tag;
        String chargebackCode;
        String maxmindId;
        UUID minfraudId;
        String notes;
        String transactionId;

        /**
         * The constructor for the {@code TransactionReport.Builder} class
         *
         * @param ipAddress The IP address associated with the device used
         *                  by the customer in the transaction.
         * @param tag       A string indicating the likelihood that a transaction may be
         *                  fraudulent.
         */
        public Builder(InetAddress ipAddress, Tag tag) {
            if (ipAddress == null) {
                throw new IllegalArgumentException("ipAddress must not be null");
            }
            if (tag == null) {
                throw new IllegalArgumentException("tag must not be null");
            }

            this.ipAddress = ipAddress;
            this.tag = tag;
        }

        /**
         * @param chargebackCode A string which is provided by your payment processor
         *                       indicating the reason for the chargeback.
         * @return The builder object.
         */
        public TransactionReport.Builder chargebackCode(String chargebackCode) {
            this.chargebackCode = chargebackCode;
            return this;
        }

        /**
         * @param maxmindId A unique eight character string identifying a minFraud
         *                  Standard or Premium request. These IDs are returned in the maxmindID field
         *                  of a response for a successful minFraud request. This field is not required,
         *                  but you are encouraged to provide it, if possible.
         * @return The builder object.
         */
        public TransactionReport.Builder maxmindId(String maxmindId) {
            if (maxmindId.length() != 8) {
                throw new IllegalArgumentException("maxmindId must be exactly 8 characters in length");
            }
            this.maxmindId = maxmindId;
            return this;
        }

        /**
         * @param minfraudId A UUID that identifies a minFraud Score, minFraud Insights,
         *                   or minFraud Factors request. This ID is returned via getId() in the
         *                   Score, Insights or Factors response object. This field is not
         *                   required, but you are encouraged to provide it if the request was
         *                   made to one of these services.
         * @return The builder object.
         */
        public TransactionReport.Builder minfraudId(UUID minfraudId) {
            this.minfraudId = minfraudId;
            return this;
        }

        /**
         * @param notes Your notes on the fraud tag associated with the transaction.
         * @return The builder object.
         */
        public TransactionReport.Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        /**
         * @param transactionId The transaction ID you originally passed to minFraud.
         * @return The builder object.
         */
        public TransactionReport.Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        /**
         * @return An instance of {@code TransactionReport} created from the
         * fields set on this builder.
         */
        public TransactionReport build() {
            return new TransactionReport(this);
        }
    }

    /**
     * @return The IP address used in the transaction.
     */
    @JsonProperty("ip_address")
    public InetAddress getIpAddress() {
        return ipAddress;
    }

    /**
     * @return The tag.
     */
    @JsonProperty("tag")
    public Tag getTag() {
        return tag;
    }

    /**
     * @return The chargeback code.
     */
    @JsonProperty("chargeback_code")
    public String getChargebackCode() {
        return chargebackCode;
    }

    /**
     * @return The maxmind_id.
     */
    @JsonProperty("maxmind_id")
    public String getMaxmindId() {
        return maxmindId;
    }

    /**
     * @return The minfraud_id.
     */
    @JsonProperty("minfraud_id")
    public UUID getMinfraudId() {
        return minfraudId;
    }

    /**
     * @return The notes.
     */
    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    /**
     * @return The transaction_id.
     */
    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * The enumerated tag types.
     */
    public enum Tag {
        NOT_FRAUD,
        SUSPECTED_FRAUD,
        SPAM_OR_ABUSE,
        CHARGEBACK;

        public String toString() {
            return this.name().toLowerCase();
        }
    }

}
