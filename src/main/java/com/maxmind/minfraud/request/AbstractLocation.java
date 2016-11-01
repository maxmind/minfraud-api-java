package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.util.regex.Pattern;

/**
 * This class represents the shared location behavior between
 * Billing and Shipping.
 */
public abstract class AbstractLocation extends AbstractModel {
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String address;
    private final String address2;
    private final String city;
    private final String region;
    private final String country;
    private final String postal;
    private final String phoneNumber;
    private final String phoneCountryCode;

    protected AbstractLocation(AbstractLocation.Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        company = builder.company;
        address = builder.address;
        address2 = builder.address2;
        city = builder.city;
        region = builder.region;
        country = builder.country;
        postal = builder.postal;
        phoneNumber = builder.phoneNumber;
        phoneCountryCode = builder.phoneCountryCode;
    }

    /**
     * {@code Builder} creates instances of the parent class from values set
     * by the builder's methods.
     */
    @SuppressWarnings("unchecked")
    abstract static class Builder<T extends AbstractLocation.Builder> {
        private static final Pattern COUNTRY_CODE_PATTERN = Pattern.compile("^[A-Z]{2}$");

        String firstName;
        String lastName;
        String company;
        String address;
        String address2;
        String city;
        String region;
        String country;
        String postal;
        String phoneNumber;
        String phoneCountryCode;

        /**
         * @param name The first name associated with the address
         * @return The builder object.
         */
        public final T firstName(String name) {
            firstName = name;
            return (T) this;
        }

        /**
         * @param name The last name associated with the address
         * @return The builder object.
         */
        public final T lastName(String name) {
            lastName = name;
            return (T) this;
        }

        /**
         * @param name The company name associated with the address
         * @return The builder object.
         */
        public final T company(String name) {
            company = name;
            return (T) this;
        }

        /**
         * @param address The first line of the address
         * @return The builder object.
         */
        public final T address(String address) {
            this.address = address;
            return (T) this;
        }

        /**
         * @param address2 The second line of the address
         * @return The builder object.
         */
        public final T address2(String address2) {
            this.address2 = address2;
            return (T) this;
        }

        /**
         * @param name The city associated with the address
         * @return The builder object.
         */
        public final T city(String name) {
            city = name;
            return (T) this;
        }

        /**
         * @param code The ISO 3166-2 subdivision code for the region
         *             associated with the address
         * @return The builder object.
         */
        public final T region(String code) {
            region = code;
            return (T) this;
        }

        /**
         * @param code The ISO 3166-1 alpha-2 country code for the country
         *             associated with the address (e.g, "US")
         * @return The builder object.
         * @throws IllegalArgumentException when code is not a two-letter
         *                                  country code.
         */
        public final T country(String code) {
            if (!COUNTRY_CODE_PATTERN.matcher(code).matches()) {
                throw new IllegalArgumentException("Expected two-letter country code in the ISO 3166-1 alpha-2 format");
            }
            country = code;
            return (T) this;
        }

        /**
         * @param code The postal code for associated with the address
         * @return The builder object.
         */
        public final T postal(String code) {
            postal = code;
            return (T) this;
        }

        /**
         * @param code The phone country code for the phone number associated with the address
         * @return The builder object.
         */
        public final T phoneCountryCode(String code) {
            phoneCountryCode = code;
            return (T) this;
        }

        /**
         * @param number The phone number, without the country code, associated with the address
         * @return The builder object.
         */
        public final T phoneNumber(String number) {
            phoneNumber = number;
            return (T) this;
        }

        abstract AbstractLocation build();
    }

    /**
     * @return The first name associated with the address
     */
    @JsonProperty("first_name")
    public final String getFirstName() {
        return firstName;
    }

    /**
     * @return The last name associated with the address
     */
    @JsonProperty("last_name")
    public final String getLastName() {
        return lastName;
    }

    /**
     * @return The company name associated with the address
     */
    @JsonProperty("company")
    public final String getCompany() {
        return company;
    }


    /**
     * @return The first line of the address
     */
    @JsonProperty("address")
    public final String getAddress() {
        return address;
    }


    /**
     * @return The second line of the address
     */
    @JsonProperty("address_2")
    public final String getAddress2() {
        return address2;
    }


    /**
     * @return The city associated with the address
     */
    @JsonProperty("city")
    public final String getCity() {
        return city;
    }


    /**
     * @return The region code associated with the address
     */
    @JsonProperty("region")
    public final String getRegion() {
        return region;
    }


    /**
     * @return The country associated with the address
     */
    @JsonProperty("country")
    public final String getCountry() {
        return country;
    }

    /**
     * @return The postal code associated with the address
     */
    @JsonProperty("postal")
    public final String getPostal() {
        return postal;
    }

    /**
     * @return The phone number associated with the address
     */
    @JsonProperty("phone_number")
    public final String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return The phone number country code associated with the
     * address
     */
    @JsonProperty("phone_country_code")
    public final String getPhoneCountryCode() {
        return phoneCountryCode;
    }
}
