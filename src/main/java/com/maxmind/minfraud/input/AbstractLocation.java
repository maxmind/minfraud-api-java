package com.maxmind.minfraud.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.exception.InvalidInputException;

/**
 * Shared behavior between Shipping and Billing
 */
abstract class AbstractLocation {
    @JsonProperty("first_name")
    protected String firstName;

    @JsonProperty("last_name")
    protected String lastName;

    @JsonProperty
    protected String company;
    @JsonProperty
    protected String address;
    @JsonProperty("address_2")
    protected String address2;
    @JsonProperty
    protected String city;
    @JsonProperty
    protected String region;
    @JsonProperty
    protected String country;
    @JsonProperty
    protected String postal;
    @JsonProperty("phone_number")
    protected String phoneNumber;
    @JsonProperty("phone_country_code")
    protected String phoneCountryCode;

    public AbstractLocation(AbstractLocation.Builder builder) {
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

    public abstract static class Builder<T extends AbstractLocation.Builder> {
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

        public final T firstName(String name) {
            firstName = name;
            return (T) this;
        }

        public final T lastName(String name) {
            lastName = name;
            return (T) this;
        }

        public final T company(String name) {
            company = name;
            return (T) this;
        }

        public final T address(String address) {
            this.address = address;
            return (T) this;
        }

        public final T address2(String address2) {
            this.address2 = address2;
            return (T) this;
        }

        public final T city(String name) {
            city = name;
            return (T) this;
        }

        public final T region(String code) {
            region = code;
            return (T) this;
        }

        public final T country(String code) {
            if (!code.matches("[A-Z]{2}")) {
                throw new InvalidInputException("Expected two-letter country code in the ISO 3166-1 alpha-2 format");
            }
            country = code;
            return (T) this;
        }

        public final T postal(String code) {
            postal = code;
            return (T) this;
        }

        public final T phoneNumber(String number) {
            phoneNumber = number;
            return (T) this;
        }

        public final T phoneCountryCode(String code) {
            phoneCountryCode = code;
            return (T) this;
        }

        public abstract AbstractLocation build();
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getCompany() {
        return company;
    }

    public final String getAddress() {
        return address;
    }

    public final String getAddress2() {
        return address2;
    }

    public final String getCity() {
        return city;
    }

    public final String getRegion() {
        return region;
    }

    public final String getCountry() {
        return country;
    }

    public final String getPostal() {
        return postal;
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AbstractLocation{");
        sb.append("firstName='").append(this.firstName).append('\'');
        sb.append(", lastName='").append(this.lastName).append('\'');
        sb.append(", company='").append(this.company).append('\'');
        sb.append(", address='").append(this.address).append('\'');
        sb.append(", address2='").append(this.address2).append('\'');
        sb.append(", city='").append(this.city).append('\'');
        sb.append(", region='").append(this.region).append('\'');
        sb.append(", country='").append(this.country).append('\'');
        sb.append(", postal='").append(this.postal).append('\'');
        sb.append(", phoneNumber='").append(this.phoneNumber).append('\'');
        sb.append(", phoneCountryCode='").append(this.phoneCountryCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
