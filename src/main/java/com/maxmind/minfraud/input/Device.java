package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.InetAddress;

/**
 * The device information for the transaction.
 */
public class Device {
    @JsonProperty("ip_address")
    private InetAddress ipAddress;

    @JsonProperty("user_agent")
    private String userAgent;

    @JsonProperty("accept_language")
    private String acceptLanguage;

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getAcceptLanguage() {
        return this.acceptLanguage;
    }

    public InetAddress getIpAddress() {

        return this.ipAddress;
    }

    private Device(Builder builder) {
        this.ipAddress = builder.ipAddress;
        this.userAgent = builder.userAgent;
        this.acceptLanguage = builder.acceptLanguage;
    }

    public final static class Builder {
        InetAddress ipAddress;
        String userAgent;
        String acceptLanguage;

        public Builder ipAddress(InetAddress ip) {
            this.ipAddress = ip;
            return this;
        }

        public Builder userAgent(String ua) {
            this.userAgent = ua;
            return this;
        }

        public Builder acceptLanguage(String acceptLanguage) {
            this.acceptLanguage = acceptLanguage;
            return this;
        }

        public Device build() {
            return new Device(this);
        }
    }
}
