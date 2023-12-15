package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * This class contains information about a particular event.
 */
public final class Event extends AbstractModel {

    private final String transactionId;
    private final String shopId;
    private final ZonedDateTime time;
    private final Type type;

    private Event(Event.Builder builder) {
        transactionId = builder.transactionId;
        shopId = builder.shopId;
        time = builder.time;
        type = builder.type;
    }

    /**
     * {@code Builder} creates instances of {@code Event}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        String transactionId;
        String shopId;
        ZonedDateTime time;
        Type type;

        /**
         * @param id Your internal ID for the transaction. We can use this to
         *           locate a specific transaction in our logs, and it will
         *           also show up in email alerts and notifications from us to
         *           you.
         * @return The builder object.
         */
        public Event.Builder transactionId(String id) {
            this.transactionId = id;
            return this;
        }

        /**
         * @param id Your internal ID for the shop, affiliate, or merchant
         *           this order is coming from. Required for minFraud users
         *           who are resellers, payment providers, gateways and
         *           affiliate networks.
         * @return The builder object.
         */
        public Event.Builder shopId(String id) {
            this.shopId = id;
            return this;
        }

        /**
         * @param date The date and time the event occurred.
         * @return The builder object.
         */
        public Event.Builder time(Date date) {
            time = date.toInstant().atZone(ZoneId.systemDefault());
            return this;
        }

        /**
         * @param date The date and time the event occurred.
         * @return The builder object.
         */
        public Event.Builder time(ZonedDateTime date) {
            time = date;
            return this;
        }

        /**
         * @param type The type of event being scored.
         * @return The builder object.
         */
        public Event.Builder type(Type type) {
            this.type = type;
            return this;
        }

        /**
         * @return An instance of {@code Event} created from the
         * fields set on this builder.
         */
        public Event build() {
            return new Event(this);
        }
    }

    /**
     * @return The transaction ID.
     */
    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @return The shop ID.
     */
    @JsonProperty("shop_id")
    public String getShopId() {
        return shopId;
    }

    /**
     * @return The date and time of the event.
     */
    @JsonIgnore
    public Date getTime() {
        return Date.from(time.toInstant());
    }

    /**
     * @return The date and time of the event.
     */
    @JsonProperty("time")
    public ZonedDateTime getDateTime() {
        return time;
    }

    /**
     * @return The type of the event.
     */
    @JsonProperty("type")
    public Type getType() {
        return type;
    }

    /**
     * The enumerated event types.
     */
    public enum Type {
        /** 
         * The account was created
         */
        ACCOUNT_CREATION,
        /** 
         * The account was logged into
         */
        ACCOUNT_LOGIN,
        /** 
         * The account email was changed
         */
        EMAIL_CHANGE,
        /** 
         * The account password was reset
         */
        PASSWORD_RESET,
        /** 
         * The account payout was changed
         */
        PAYOUT_CHANGE,
        /**
         * A purchase was made by the account
         */
        PURCHASE,
        /** 
         * A recurring purchase was made by the account
         */
        RECURRING_PURCHASE,
        /** 
         * A referral was made by the account
         */
        REFERRAL,
        /** 
         * A survey was completed by the account
         */
        SURVEY;

        public String toString() {
            return this.name().toLowerCase();
        }
    }
}


