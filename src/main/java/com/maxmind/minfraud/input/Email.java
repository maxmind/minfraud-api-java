package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.MinFraudRequest;
import com.maxmind.minfraud.exception.InvalidInputException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * The email information for the transaction.
 */
public class Email  {
    @JsonProperty
    private final String address;

    @JsonProperty
    private final String domain;

    public Email(Builder builder) {
        this.address =          builder.address;
        this.domain = builder.domain;
    }

    public final static class Builder {
        private String address;
        private String domain;

        public Builder address(String address) {
            if (!EmailValidator.getInstance().isValid(address)) {
                throw new InvalidInputException("The email address " + address + " is not valid.");
            }

            if (domain == null) {
                domain = address.substring(address.indexOf('@') + 1);
            }
            this.address = DigestUtils.md5Hex(address);
            return this;
        }

        public Builder domain(String domain) {
            if (!DomainValidator.getInstance().isValid(domain)) {
                throw new InvalidInputException("The email domain " + domain + " is not valid.");
            }
            this.domain = domain;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}