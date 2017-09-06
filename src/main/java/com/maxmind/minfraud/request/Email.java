package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * The email information for the transaction.
 */
public final class Email extends AbstractModel {
    private final String address;
    private final String domain;

    private Email(Email.Builder builder) {
        address = builder.address;
        domain = builder.domain;
    }

    /**
     * {@code Builder} creates instances of {@code Email}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        private String address;
        private String domain;

        /**
         * Set the email address and domain fields for the request. If
         * you set the email address from this method, you do <em>not</em>
         * need to set the domain separately. The domain will be set to
         * the domain of the email address and the address field will be
         * set to the email address passed.
         *
         * The email address will be sent. If you want to sent only the MD5 hash
         * of the email address, use {@link #addressMd5(String)} instead.
         *
         * @param address The valid email address used in the transaction.
         * @return The builder object.
         * @throws IllegalArgumentException when address is not a valid email
         *                                  address.
         */
        public Email.Builder address(String address) {
            if (!EmailValidator.getInstance().isValid(address)) {
                throw new IllegalArgumentException("The email address " + address + " is not valid.");
            }

            if (this.domain == null) {
                this.domain = address.substring(address.indexOf('@') + 1);
            }
            this.address = address;
            return this;
        }

        /**
         * Set the email address and domain fields for the request. If
         * you set the email address from this method, you do <em>not</em>
         * need to set the domain separately. The domain will be set to
         * the domain of the email address and the address field will be
         * set to the MD5 of the email address passed.
         *
         * The MD5 of the email address will be sent. If you want to send the
         * raw email address, use {@link #address(String)} instead.
         *
         * @param address The valid email address used in the transaction.
         * @return The builder object.
         * @throws IllegalArgumentException when address is not a valid email
         *                                  address.
         */
        public Email.Builder addressMd5(String address) {
            if (!EmailValidator.getInstance().isValid(address)) {
                throw new IllegalArgumentException("The email address " + address + " is not valid.");
            }

            if (this.domain == null) {
                this.domain = address.substring(address.indexOf('@') + 1);
            }
            this.address = DigestUtils.md5Hex(address);
            return this;
        }

        /**
         * @param domain The domain of the email address. This only needs
         *               to be set if the email address is not set.
         * @return The builder object.
         * @throws IllegalArgumentException when domain is not a valid domain.
         */
        public Email.Builder domain(String domain) {
            if (!DomainValidator.getInstance().isValid(domain)) {
                throw new IllegalArgumentException("The email domain " + domain + " is not valid.");
            }
            this.domain = domain;
            return this;
        }

        /**
         * @return An instance of {@code Email} created from the
         * fields set on this builder.
         */
        public Email build() {
            return new Email(this);
        }
    }

    /**
     * @return The email address field to use in the transaction. This will be
     * a valid email address (if you used {@link #address(String)}), an MD5 hash
     * (if you used {@link #addressMd5(String)}), or null if you did not set an
     * email address.
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * @return The domain of the email address used in the transaction.
     */
    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }
}
