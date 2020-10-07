package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.net.InetAddress;

/**
 * The device information for the transaction.
 */
public final class Device extends AbstractModel {
    private final InetAddress ipAddress;
    private final String userAgent;
    private final String acceptLanguage;
    private final Double sessionAge;
    private final String sessionId;

    private Device(Device.Builder builder) {
        ipAddress = builder.ipAddress;
        userAgent = builder.userAgent;
        acceptLanguage = builder.acceptLanguage;
        sessionAge = builder.sessionAge;
        sessionId = builder.sessionId;
    }

    /**
     * {@code Builder} creates instances of {@code Device}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        InetAddress ipAddress;
        String userAgent;
        String acceptLanguage;
        Double sessionAge;
        String sessionId;

        /**
         * Constructor for the {@code Device.Builder} class
         */
        public Builder() {
        }

        /**
         * Constructor for the {@code Device.Builder} class
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
         * @param ipAddress The IP address associated with the device used
         *                  by the customer in the transaction.
         * @return The builder object.
         */
        public Device.Builder ipAddress(InetAddress ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * @param sessionAge The number of seconds between the creation of the
         *                   user's session and the time of the transaction.
         *                   Note that session_age is not the duration of the
         *                   current visit, but the time since the start of the
         *                   first visit.
         * @return The builder object.
         */
        public Device.Builder sessionAge(Double sessionAge) {
            this.sessionAge =
                    sessionAge;
            return this;
        }

        /**
         * @param sessionId A string up to 255 characters in length. This is
         *                  an ID which uniquely identifies a visitor's
         *                  session on the site.
         * @return The builder object.
         */
        public Device.Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
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
    @JsonProperty("user_agent")
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @return The "Accept-Language" header.
     */
    @JsonProperty("accept_language")
    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    /**
     * @return The session age.
     */
    @JsonProperty("session_age")
    public Double getSessionAge() {
        return sessionAge;
    }

    /**
     * @return The session id.
     */
    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @return The IP address used in the transaction.
     */
    @JsonProperty("ip_address")
    public InetAddress getIpAddress() {
        return ipAddress;
    }
}
