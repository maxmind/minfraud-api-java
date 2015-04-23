package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Event {

    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("shop_id")
    private String shopId;
    @JsonProperty("time")
    private String time;
    @JsonProperty("type")
    private Event.Type type;

    @JsonIgnore
    private SimpleDateFormat dateFormat;


    public Event(Builder builder) {
        this.transactionId = builder.transactionId;
        this.shopId = builder.shopId;
        this.time = builder.time;
        this.type = builder.type;
        this.dateFormat = builder.dateFormat;
    }

    public enum Type {
        ACCOUNT_CREATION,
        ACCOUNT_LOGIN,
        PURCHASE,
        RECURRING_PURCHASE,
        REFERRAL,
        SURVEY;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public String getShopId() {
        return this.shopId;
    }

    @JsonIgnore
    public Date getTime() throws ParseException {
        return dateFormat.parse(this.time);
    }

    public Event.Type getType() {
        return this.type;
    }

    public final static class Builder {
        String transactionId;
        String shopId;
        String time;
        Event.Type type;
        SimpleDateFormat dateFormat;

        public Builder() {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
            dateFormat.setTimeZone(tz);
        }

        public Builder transactionId(String id) {
            transactionId = id;
            return this;
        }

        public Builder shopId(String id) {
            shopId = id;
            return this;
        }

        public Builder time(Date date) {
            this.time = dateFormat.format(date);
            return this;
        }

        public Builder type(Event.Type type) {
            this.type = type;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
