package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.InetAddress;

/**
 * The device information for the transaction.
 */
public final class Device {
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

    /**
     * {@code Builder} creates instances of {@code Device}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        InetAddress ipAddress;
        String userAgent;
        String acceptLanguage;

        /**
         * The constructor for the {@code Device.Builder} class
         *
         * @param ipAddress The IP address associated with the device used
         *                  by the customer in the transaction.
         */
        public Builder(InetAddress ipAddress) {
            this.ipAddress = ipAddress;
        }

        /**
         * @param ua The HTTP “User-Agent” header of the browser used in
         *           the transaction.
         * @return The builder object.
         */
        public Device.Builder userAgent(String ua) {
            userAgent = ua;
            return this;
        }

        /**
         * @param acceptLanguage The HTTP “Accept-Language” header of the
         *                       device used in the transaction.
         * @return The builder object.
         */
        public Device.Builder acceptLanguage(String acceptLanguage) {
            this.acceptLanguage = acceptLanguage;
            return this;
        }

        /**
         * @return An instance of {@code Device} created from the
         * fields set on this builder.
         */
        public Device build() {
            return new Device(this);
        }
    }

    /**
     * @return The "User-Agent" header.
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @return The "Accept-Language" header.
     */
    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    /**
     * @return The IP address used in the transaction.
     */
    public InetAddress getIpAddress() {

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
