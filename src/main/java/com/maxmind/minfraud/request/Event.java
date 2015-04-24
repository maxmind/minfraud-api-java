package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Event {

    @JsonProperty("transaction_id")
    private final String transactionId;
    @JsonProperty("shop_id")
    private final String shopId;
    @JsonProperty("time")
    private final String time;
    @JsonProperty("type")
    private final Type type;

    @JsonIgnore
    private final SimpleDateFormat dateFormat;


    public Event(Event.Builder builder) {
        transactionId = builder.transactionId;
        shopId = builder.shopId;
        time = builder.time;
        type = builder.type;
        dateFormat = builder.dateFormat;
    }

    public static final class Builder {
        String transactionId;
        String shopId;
        String time;
        Type type;
        SimpleDateFormat dateFormat;

        public Builder() {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
            this.dateFormat.setTimeZone(tz);
        }

        public Event.Builder transactionId(String id) {
            this.transactionId = id;
            return this;
        }

        public Event.Builder shopId(String id) {
            this.shopId = id;
            return this;
        }

        public Event.Builder time(Date date) {
            time = this.dateFormat.format(date);
            return this;
        }

        public Event.Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }

    public final String getTransactionId() {
        return transactionId;
    }

    public final String getShopId() {
        return shopId;
    }

    @JsonIgnore
    public final Date getTime() throws ParseException {
        return this.dateFormat.parse(time);
    }

    public final Type getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Event{");
        sb.append("transactionId='").append(this.transactionId).append('\'');
        sb.append(", shopId='").append(this.shopId).append('\'');
        sb.append(", time='").append(this.time).append('\'');
        sb.append(", type=").append(this.type);
        sb.append('}');
        return sb.toString();
    }

    public enum Type {
        ACCOUNT_CREATION,
        ACCOUNT_LOGIN,
        PURCHASE,
        RECURRING_PURCHASE,
        REFERRAL,
        SURVEY;

        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
