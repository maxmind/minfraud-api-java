package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.InetAddress;

/**
 * The device information for the transaction.
 */
public class Device {
    @JsonProperty("ip_address")
    private final InetAddress ipAddress;

    @JsonProperty("user_agent")
    private final String userAgent;

    @JsonProperty("accept_language")
    private final String acceptLanguage;

    private Device(Device.Builder builder) {
        ipAddress = builder.ipAddress;
        userAgent = builder.userAgent;
        acceptLanguage = builder.acceptLanguage;
    }

    public static final class Builder {
        InetAddress ipAddress;
        String userAgent;
        String acceptLanguage;

        public Builder(InetAddress ipAddress) {
            this.ipAddress = ipAddress;
        }

        public Device.Builder userAgent(String ua) {
            userAgent = ua;
            return this;
        }

        public Device.Builder acceptLanguage(String acceptLanguage) {
            this.acceptLanguage = acceptLanguage;
            return this;
        }

        public Device build() {
            return new Device(this);
        }
    }

    public final String getUserAgent() {
        return userAgent;
    }

    public final String getAcceptLanguage() {
        return acceptLanguage;
    }

    public final InetAddress getIpAddress() {

        return ipAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Device{");
        sb.append("ipAddress=").append(this.ipAddress);
        sb.append(", userAgent='").append(this.userAgent).append('\'');
        sb.append(", acceptLanguage='").append(this.acceptLanguage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
