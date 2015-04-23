package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Account related data for the minFraud request
 */
public class Account {
    @JsonProperty("user_id")
    private final String userId;

    @JsonProperty("username_md5")
    private final String usernameMd5;

    private Account(Builder builder) {
        this.userId = builder.userId;
        this.usernameMd5 = builder.usernameMd5;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsernameMd5() {
        return this.usernameMd5;
    }

    public final static class Builder {
        String userId;
        String usernameMd5;

        public Builder userId(String id) {
            userId = id;
            return this;
        }

        public Builder username(String username) {
            usernameMd5 = DigestUtils.md5Hex(username);
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}