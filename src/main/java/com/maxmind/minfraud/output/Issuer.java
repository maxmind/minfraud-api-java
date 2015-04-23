package com.maxmind.minfraud.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Issuer {
    protected String name;

    @JsonProperty("matches_provided_name")
    protected Boolean matchesProvidedName;

    @JsonProperty("phone_number")
    protected String phoneNumber;

    @JsonProperty("matches_provided_phone_number")
    protected Boolean matchesProvidedPhoneNumber;

    public String getName() {
        return this.name;
    }

    public Boolean matchesProvidedName() {
        return this.matchesProvidedName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Boolean matchesProvidedPhoneNumber() {
        return this.matchesProvidedPhoneNumber;
    }

    @Override
    public String toString() {
        return "Issuer{" +
                "name='" + name + '\'' +
                ", matchesProvidedName=" + matchesProvidedName +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", matchesProvidedPhoneNumber=" + matchesProvidedPhoneNumber +
                '}';
    }
}
