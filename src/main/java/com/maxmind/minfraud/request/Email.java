package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * The email information for the transaction.
 */
public class Email {
    @JsonProperty
    private final String address;

    @JsonProperty
    private final String domain;

    public Email(Email.Builder builder) {
        address = builder.address;
        domain = builder.domain;
    }

    public static final class Builder {
        private String address;
        private String domain;

        public Email.Builder address(String address) {
            if (!EmailValidator.getInstance().isValid(address)) {
                throw new IllegalArgumentException("The email address " + address + " is not valid.");
            }

            if (this.domain == null) {
                this.domain = address.substring(address.indexOf('@') + 1);
            }
            this.address = DigestUtils.md5Hex(address);
            return this;
        }

        public Email.Builder domain(String domain) {
            if (!DomainValidator.getInstance().isValid(domain)) {
                throw new IllegalArgumentException("The email domain " + domain + " is not valid.");
            }
            this.domain = domain;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }

    public final String getAddress() {
        return address;
    }

    public final String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Email{");
        sb.append("address='").append(this.address).append('\'');
        sb.append(", domain='").append(this.domain).append('\'');
        sb.append('}');
        return sb.toString();
    }
}