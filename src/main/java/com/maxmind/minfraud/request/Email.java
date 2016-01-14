package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * The email information for the transaction.
 */
public final class Email {
    @JsonProperty("address")
    private final String addressMd5;

    @JsonProperty
    private final String domain;

    private Email(Email.Builder builder) {
        addressMd5 = builder.addressMd5;
        domain = builder.domain;
    }

    /**
     * {@code Builder} creates instances of {@code Email}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        private String addressMd5;
        private String domain;

        /**
         * Set the email address and domain fields for the request. If
         * you set the email address from this method, you do <em>not</em>
         * need to set the domain separately. The domain will be set to
         * the domain of the email address and the addressMd5 field will be
         * set to the MD5 of the email address passed.
         *
         * @param address The valid email address used in the transaction.
         * @return The builder object.
         * @throws IllegalArgumentException when address is not a valid email
         *         address.
         */
        public Email.Builder address(String address) {
            if (!EmailValidator.getInstance().isValid(address)) {
                throw new IllegalArgumentException("The email address " + address + " is not valid.");
            }

            if (this.domain == null) {
                this.domain = address.substring(address.indexOf('@') + 1);
            }
            this.addressMd5 = DigestUtils.md5Hex(address);
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
     * @return The MD5 of the email address used in the transaction.
     */
    public String getAddressMd5() {
        return addressMd5;
    }

    /**
     * @return The domain of the email address used in the transaction.
     */
    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Email{");
        sb.append("addressMd5='").append(this.addressMd5).append('\'');
        sb.append(", domain='").append(this.domain).append('\'');
        sb.append('}');
        return sb.toString();
    }
}