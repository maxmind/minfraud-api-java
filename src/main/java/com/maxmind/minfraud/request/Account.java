package com.maxmind.minfraud.request;

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

    private Account(Account.Builder builder) {
        userId = builder.userId;
        usernameMd5 = builder.usernameMd5;
    }

    public static final class Builder {
        String userId;
        String usernameMd5;

        public Account.Builder userId(String id) {
            this.userId = id;
            return this;
        }

        public Account.Builder username(String username) {
            this.usernameMd5 = DigestUtils.md5Hex(username);
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    public final String getUserId() {
        return userId;
    }

    public final String getUsernameMd5() {
        return usernameMd5;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Account{");
        sb.append("userId='").append(this.userId).append('\'');
        sb.append(", usernameMd5='").append(this.usernameMd5).append('\'');
        sb.append('}');
        return sb.toString();
    }
}