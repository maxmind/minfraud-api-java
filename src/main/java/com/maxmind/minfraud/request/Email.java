package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;
import java.math.BigInteger;
import java.net.IDN;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * The email information for the transaction.
 */
public final class Email extends AbstractModel {
    private final String address;
    private final boolean hashAddress;
    private final String domain;
    private static final Map<String, String> typoDomains;
    private static final Map<String, String> typoTlds;
    private static final Map<String, String> equivalentDomains;
    private static final Map<String, Boolean> fastmailDomains;
    private static final Map<String, Boolean> yahooDomains;
    private static final Pattern DOMAIN_LABEL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9]$|^[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9]$"
    );
    private static final Pattern DOT_PATTERN = Pattern.compile("\\.");
    private static final Pattern TRAILING_DOT_PATTERN = Pattern.compile("\\.+$");
    private static final Pattern REPEAT_COM_PATTERN = Pattern.compile("(?:\\.com){2,}$");
    private static final Pattern GMAIL_NUMBER_PATTERN = Pattern.compile("^\\d+(?:gmail?\\.com)$");

    static {
        HashMap<String, String> typoDomainsMap = new HashMap<>() {{
                // gmail.com
                put("gmai.com", "gmail.com");
                put("gamil.com", "gmail.com");
                put("gmali.com", "gmail.com");
                put("gmial.com", "gmail.com");
                put("gmil.com", "gmail.com");
                put("gmaill.com", "gmail.com");
                put("gmailm.com", "gmail.com");
                put("gmailo.com", "gmail.com");
                put("gmailyhoo.com", "gmail.com");
                put("yahoogmail.com", "gmail.com");
                // outlook.com
                put("putlook.com", "outlook.com");
            }};
        typoDomains = Collections.unmodifiableMap(typoDomainsMap);

        HashMap<String, String> typoTldsMap = new HashMap<>() {{
                put("comm", "com");
                put("commm", "com");
                put("commmm", "com");
                put("comn", "com");

                put("cbm", "com");
                put("ccm", "com");
                put("cdm", "com");
                put("cem", "com");
                put("cfm", "com");
                put("cgm", "com");
                put("chm", "com");
                put("cim", "com");
                put("cjm", "com");
                put("ckm", "com");
                put("clm", "com");
                put("cmm", "com");
                put("cnm", "com");
                put("cpm", "com");
                put("cqm", "com");
                put("crm", "com");
                put("csm", "com");
                put("ctm", "com");
                put("cum", "com");
                put("cvm", "com");
                put("cwm", "com");
                put("cxm", "com");
                put("cym", "com");
                put("czm", "com");

                put("col", "com");
                put("con", "com");

                put("dom", "com");
                put("don", "com");
                put("som", "com");
                put("son", "com");
                put("vom", "com");
                put("von", "com");
                put("xom", "com");
                put("xon", "com");

                put("clam", "com");
                put("colm", "com");
                put("comcom", "com");
            }};
        typoTlds = Collections.unmodifiableMap(typoTldsMap);

        HashMap<String, String> equivalentDomainsMap = new HashMap<>() {{
                put("googlemail.com", "gmail.com");
                put("pm.me", "protonmail.com");
                put("proton.me", "protonmail.com");
                put("yandex.by", "yandex.ru");
                put("yandex.com", "yandex.ru");
                put("yandex.kz", "yandex.ru");
                put("yandex.ua", "yandex.ru");
                put("ya.ru", "yandex.ru");
            }};
        equivalentDomains = Collections.unmodifiableMap(equivalentDomainsMap);

        HashMap<String, Boolean> fastmailDomainsMap = new HashMap<>() {{
                put("123mail.org", true);
                put("150mail.com", true);
                put("150ml.com", true);
                put("16mail.com", true);
                put("2-mail.com", true);
                put("4email.net", true);
                put("50mail.com", true);
                put("airpost.net", true);
                put("allmail.net", true);
                put("bestmail.us", true);
                put("cluemail.com", true);
                put("elitemail.org", true);
                put("emailcorner.net", true);
                put("emailengine.net", true);
                put("emailengine.org", true);
                put("emailgroups.net", true);
                put("emailplus.org", true);
                put("emailuser.net", true);
                put("eml.cc", true);
                put("f-m.fm", true);
                put("fast-email.com", true);
                put("fast-mail.org", true);
                put("fastem.com", true);
                put("fastemail.us", true);
                put("fastemailer.com", true);
                put("fastest.cc", true);
                put("fastimap.com", true);
                put("fastmail.cn", true);
                put("fastmail.co.uk", true);
                put("fastmail.com", true);
                put("fastmail.com.au", true);
                put("fastmail.de", true);
                put("fastmail.es", true);
                put("fastmail.fm", true);
                put("fastmail.fr", true);
                put("fastmail.im", true);
                put("fastmail.in", true);
                put("fastmail.jp", true);
                put("fastmail.mx", true);
                put("fastmail.net", true);
                put("fastmail.nl", true);
                put("fastmail.org", true);
                put("fastmail.se", true);
                put("fastmail.to", true);
                put("fastmail.tw", true);
                put("fastmail.uk", true);
                put("fastmail.us", true);
                put("fastmailbox.net", true);
                put("fastmessaging.com", true);
                put("fea.st", true);
                put("fmail.co.uk", true);
                put("fmailbox.com", true);
                put("fmgirl.com", true);
                put("fmguy.com", true);
                put("ftml.net", true);
                put("h-mail.us", true);
                put("hailmail.net", true);
                put("imap-mail.com", true);
                put("imap.cc", true);
                put("imapmail.org", true);
                put("inoutbox.com", true);
                put("internet-e-mail.com", true);
                put("internet-mail.org", true);
                put("internetemails.net", true);
                put("internetmailing.net", true);
                put("jetemail.net", true);
                put("justemail.net", true);
                put("letterboxes.org", true);
                put("mail-central.com", true);
                put("mail-page.com", true);
                put("mailandftp.com", true);
                put("mailas.com", true);
                put("mailbolt.com", true);
                put("mailc.net", true);
                put("mailcan.com", true);
                put("mailforce.net", true);
                put("mailftp.com", true);
                put("mailhaven.com", true);
                put("mailingaddress.org", true);
                put("mailite.com", true);
                put("mailmight.com", true);
                put("mailnew.com", true);
                put("mailsent.net", true);
                put("mailservice.ms", true);
                put("mailup.net", true);
                put("mailworks.org", true);
                put("ml1.net", true);
                put("mm.st", true);
                put("myfastmail.com", true);
                put("mymacmail.com", true);
                put("nospammail.net", true);
                put("ownmail.net", true);
                put("petml.com", true);
                put("postinbox.com", true);
                put("postpro.net", true);
                put("proinbox.com", true);
                put("promessage.com", true);
                put("realemail.net", true);
                put("reallyfast.biz", true);
                put("reallyfast.info", true);
                put("rushpost.com", true);
                put("sent.as", true);
                put("sent.at", true);
                put("sent.com", true);
                put("speedpost.net", true);
                put("speedymail.org", true);
                put("ssl-mail.com", true);
                put("swift-mail.com", true);
                put("the-fastest.net", true);
                put("the-quickest.com", true);
                put("theinternetemail.com", true);
                put("veryfast.biz", true);
                put("veryspeedy.net", true);
                put("warpmail.net", true);
                put("xsmail.com", true);
                put("yepmail.net", true);
                put("your-mail.com", true);
            }};
        fastmailDomains = Collections.unmodifiableMap(fastmailDomainsMap);

        HashMap<String, Boolean> yahooDomainsMap = new HashMap<>() {{
                put("y7mail.com", true);
                put("yahoo.at", true);
                put("yahoo.be", true);
                put("yahoo.bg", true);
                put("yahoo.ca", true);
                put("yahoo.cl", true);
                put("yahoo.co.id", true);
                put("yahoo.co.il", true);
                put("yahoo.co.in", true);
                put("yahoo.co.kr", true);
                put("yahoo.co.nz", true);
                put("yahoo.co.th", true);
                put("yahoo.co.uk", true);
                put("yahoo.co.za", true);
                put("yahoo.com", true);
                put("yahoo.com.ar", true);
                put("yahoo.com.au", true);
                put("yahoo.com.br", true);
                put("yahoo.com.co", true);
                put("yahoo.com.hk", true);
                put("yahoo.com.hr", true);
                put("yahoo.com.mx", true);
                put("yahoo.com.my", true);
                put("yahoo.com.pe", true);
                put("yahoo.com.ph", true);
                put("yahoo.com.sg", true);
                put("yahoo.com.tr", true);
                put("yahoo.com.tw", true);
                put("yahoo.com.ua", true);
                put("yahoo.com.ve", true);
                put("yahoo.com.vn", true);
                put("yahoo.cz", true);
                put("yahoo.de", true);
                put("yahoo.dk", true);
                put("yahoo.ee", true);
                put("yahoo.es", true);
                put("yahoo.fi", true);
                put("yahoo.fr", true);
                put("yahoo.gr", true);
                put("yahoo.hu", true);
                put("yahoo.ie", true);
                put("yahoo.in", true);
                put("yahoo.it", true);
                put("yahoo.lt", true);
                put("yahoo.lv", true);
                put("yahoo.nl", true);
                put("yahoo.no", true);
                put("yahoo.pl", true);
                put("yahoo.pt", true);
                put("yahoo.ro", true);
                put("yahoo.se", true);
                put("yahoo.sk", true);
                put("ymail.com", true);
            }};
        yahooDomains = Collections.unmodifiableMap(yahooDomainsMap);
    }

    private Email(Email.Builder builder) {
        address = builder.address;
        hashAddress = builder.hashAddress;
        domain = builder.domain;
    }

    /**
     * {@code Builder} creates instances of {@code Email} from values set by the builder's methods.
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
         * Set the email address and domain fields for the request. If you set the email address
         * from this method, you do <em>not</em> need to set the domain separately. The domain will
         * be set to the domain of the email address and the address field will be set to the email
         * address passed.
         * <p>
         * The email address will be sent in plain text unless you also call {@link #hashAddress()}
         * to instead send it as an MD5 hash.
         *
         * @param address The valid email address used in the transaction.
         * @return The builder object.
         * @throws IllegalArgumentException when address is not a valid email address.
         */
        public Email.Builder address(String address) {
            if (enableValidation && !isValidEmail(address)) {
                throw new IllegalArgumentException(
                    "The email address " + address + " is not valid.");
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
         * By default, the email address set by {@link #address(String)} will be sent in plain text.
         * Enable sending it as an MD5 hash instead by calling this method.
         *
         * @return The builder object.
         */
        public Email.Builder hashAddress() {
            this.hashAddress = true;
            return this;
        }

        /**
         * @param domain The domain of the email address. This only needs to be set if the email
         *               address is not set.
         * @return The builder object.
         * @throws IllegalArgumentException when domain is not a valid domain.
         */
        public Email.Builder domain(String domain) {
            if (enableValidation && !isValidDomain(domain)) {
                throw new IllegalArgumentException("The email domain " + domain + " is not valid.");
            }
            this.domain = domain;
            return this;
        }

        /**
         * @return An instance of {@code Email} created from the fields set on this builder.
         */
        public Email build() {
            return new Email(this);
        }
    }

    /**
     * @return The email address field to use in the transaction. This will be a valid email address
     *     if you used {@link Builder#address(String)}, an MD5 hash if you used
     *     {@link Builder#hashAddress()} as well, or null if you did not set an email address.
     */
    @JsonProperty("address")
    public String address() {
        if (address == null) {
            return null;
        }
        if (hashAddress) {
            String cleanAddress = cleanAddress(address);
            try {
                MessageDigest d = MessageDigest.getInstance("MD5");
                d.update(cleanAddress.getBytes(StandardCharsets.UTF_8));
                BigInteger i = new BigInteger(1, d.digest());
                return String.format("%032x", i);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("No MD5 algorithm for MessageDigest!", e);
            }
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

        localPart = Normalizer.normalize(localPart, Normalizer.Form.NFC);
        domain = cleanDomain(domain);

        int stopChar;
        if (yahooDomains.containsKey(domain)) {
            stopChar = '-';
        } else {
            stopChar = '+';
        }
        int stopCharIndex = localPart.indexOf(stopChar);
        if (stopCharIndex > 0) {
            localPart = localPart.substring(0, stopCharIndex);
        }

        if (domain.equals("gmail.com")) {
            localPart = localPart.replace(".", "");
        }

        String[] domainParts = DOT_PATTERN.split(domain);
        if (domainParts.length > 2) {
            String possibleDomain = String.join(
                ".",
                Arrays.copyOfRange(domainParts, 1, domainParts.length)
            );
            if (fastmailDomains.containsKey(possibleDomain)) {
                domain = possibleDomain;
                if (!localPart.isEmpty()) {
                    localPart = domainParts[0];
                }
            }
        }

        return localPart + "@" + domain;
    }

    private static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // In RFC 5321, the forward path limits the mailbox to 254 characters
        // even though a domain can be 255 and the local part 64
        if (email.length() > 254) {
            return false;
        }

        int atIndex = email.lastIndexOf('@');
        if (atIndex <= 0) {
            return false;
        }

        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1);

        // The local-part has a maximum length of 64 characters.
        if (localPart.length() > 64) {
            return false;
        }

        return isValidDomain(domainPart);
    }


    private String cleanDomain(String domain) {
        if (domain == null) {
            return null;
        }

        domain = domain.trim();

        domain = TRAILING_DOT_PATTERN.matcher(domain).replaceAll("");

        domain = IDN.toASCII(domain);

        domain = REPEAT_COM_PATTERN.matcher(domain).replaceAll(".com");
        domain = GMAIL_NUMBER_PATTERN.matcher(domain).replaceAll("gmail.com");

        int idx = domain.lastIndexOf('.');
        if (idx != -1) {
            String tld = domain.substring(idx + 1);
            if (typoTlds.containsKey(tld)) {
                domain = domain.substring(0, idx) + "." + typoTlds.get(tld);
            }
        }

        if (typoDomains.containsKey(domain)) {
            domain = typoDomains.get(domain);
        }

        if (equivalentDomains.containsKey(domain)) {
            domain = equivalentDomains.get(domain);
        }

        return domain;
    }

    private static boolean isValidDomain(String domain) {
        if (domain == null || domain.isEmpty()) {
            return false;
        }

        try {
            domain = IDN.toASCII(domain);
        } catch (IllegalArgumentException e) {
            return false;
        }

        if (domain.endsWith(".")) {
            domain = domain.substring(0, domain.length() - 1);
        }

        if (domain.length() > 255) {
            return false;
        }

        String[] labels = domain.split("\\.");

        if (labels.length < 2) {
            return false;
        }

        for (String label : labels) {
            if (!isValidDomainLabel(label)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidDomainLabel(String label) {
        if (label == null || label.isEmpty() || label.length() > 63) {
            return false;
        }

        return DOMAIN_LABEL_PATTERN.matcher(label).matches();
    }

    /**
     * @return The domain of the email address used in the transaction.
     */
    @JsonProperty("domain")
    public String domain() {
        return domain;
    }
}
