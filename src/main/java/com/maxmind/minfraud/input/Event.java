package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by greg on 4/20/15.
 */
public class Event {

    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("shop_id")
    private String shopId;
    @JsonProperty("time")
    private String time;
    @JsonProperty("type")
    private Type type;

    public Event(Builder builder) {
        this.transactionId = builder.transactionId;
        this.shopId = builder.shopId;
        this.time = builder.time;
        this.type = builder.type;
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

    public final static class Builder {
        String transactionId;
        String shopId;
        String time;
        Type type;

        public Builder transactionId(String id) {
            transactionId = id;
            return this;
        }

        public Builder shopId(String id) {
            shopId = id;
            return this;
        }

        public Builder time(Date date) {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSXXX");
            df.setTimeZone(tz);
            this.time = df.format(date);
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
