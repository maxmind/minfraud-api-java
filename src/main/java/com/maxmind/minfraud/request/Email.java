package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;

import java.net.IDN;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The email information for the transaction.
 */
public final class Email extends AbstractModel {
    private final String address;
    private final boolean hashAddress;
    private final String domain;
    private static final Map<String, String> typoDomains;

    static {
        HashMap<String, String> m = new HashMap<>() {{
            // gmail.com
            put("35gmai.com", "gmail.com");
            put("636gmail.com", "gmail.com");
            put("gamil.com", "gmail.com");
            put("gmail.comu", "gmail.com");
            put("gmial.com", "gmail.com");
            put("gmil.com", "gmail.com");
            put("yahoogmail.com", "gmail.com");
            // outlook.com
            put("putlook.com", "outlook.com");
        }};

        typoDomains = Collections.unmodifiableMap(m);
    }

    private Email(Email.Builder builder) {
        address = builder.address;
        hashAddress = builder.hashAddress;
        domain = builder.domain;
    }

    /**
     * {@code Builder} creates instances of {@code Email}
     * from values set by the builder's methods.
     */
    public static final class Builder {
        private final boolean enableValidation;
        private String address;
        private boolean hashAddress;
        private String domain;

        /**
         * The constructor for the builder.
         * <p>
         * By default, validation will be enabled.
         */
        public Builder() {
            enableValidation = true;
        }

        /**
         * The constructor for the builder.
         *
         * @param enableValidation Whether validation should be enabled.
         */
        public Builder(boolean enableValidation) {
            this.enableValidation = enableValidation;
        }

        /**
         * Set the email address and domain fields for the request. If
         * you set the email address from this method, you do <em>not</em>
         * need to set the domain separately. The domain will be set to
         * the domain of the email address and the address field will be
         * set to the email address passed.
         * <p>
         * The email address will be sent in plain text unless you also call
         * {@link #hashAddress()} to instead send it as an MD5 hash.
         *
         * @param address The valid email address used in the transaction.
         * @return The builder object.
         * @throws IllegalArgumentException when address is not a valid email
         *                                  address.
         */
        public Email.Builder address(String address) {
            if (enableValidation && !EmailValidator.getInstance().isValid(address)) {
                throw new IllegalArgumentException("The email address " + address + " is not valid.");
            }

            if (this.domain == null) {
                int domainIndex = address.lastIndexOf('@') + 1;
                if (domainIndex > 0 && domainIndex < address.length()) {
                    this.domain = address.substring(domainIndex);
                }
            }
            this.address = address;
            return this;
        }

        /**
         * Send the email address as its MD5 hash.
         * <p>
         * By default, the email address set by {@link #address(String)} will be
         * sent in plain text. Enable sending it as an MD5 hash instead by
         * calling this method.
         *
         * @return The builder object.
         */
        public Email.Builder hashAddress() {
            this.hashAddress = true;
            return this;
        }

        /**
         * @param domain The domain of the email address. This only needs
         *               to be set if the email address is not set.
         * @return The builder object.
         * @throws IllegalArgumentException when domain is not a valid domain.
         */
        public Email.Builder domain(String domain) {
            if (enableValidation && !DomainValidator.getInstance().isValid(domain)) {
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
     * a valid email address if you used {@link Builder#address(String)}, an MD5
     * hash if you used {@link Builder#hashAddress()} as well, or null if you
     * did not set an email address.
     */
    @JsonProperty("address")
    public String getAddress() {
        if (address == null) {
            return null;
        }
        if (hashAddress) {
            return DigestUtils.md5Hex(cleanAddress(address));
        }
        return address;
    }

    private String cleanAddress(String address) {
        address = address.trim().toLowerCase();

        int domainIndex = address.lastIndexOf('@');
        if (domainIndex == -1 || domainIndex + 1 == address.length()) {
            return address;
        }

        String localPart = address.substring(0, domainIndex);
        String domain = address.substring(domainIndex + 1);

        domain = cleanDomain(domain);

        int stopChar;
        if (domain.equals("yahoo.com")) {
            stopChar = '-';
        } else {
            stopChar = '+';
        }
        int stopCharIndex = localPart.indexOf(stopChar);
        if (stopCharIndex > 0) {
            localPart = localPart.substring(0, stopCharIndex);
        }

        return localPart + "@" + domain;
    }

    private String cleanDomain(String domain) {
        if (domain == null) {
            return null;
        }

        domain = domain.trim();

        if (domain.endsWith(".")) {
            domain = domain.substring(0, domain.length() - 1);
        }

        domain = IDN.toASCII(domain);

        if (typoDomains.containsKey(domain)) {
            domain = typoDomains.get(domain);
        }

        return domain;
    }

    /**
     * @return The domain of the email address used in the transaction.
     */
    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }
}
