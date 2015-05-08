package com.maxmind.minfraud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Issuer {
    protected String name;

    @JsonProperty("matches_provided_name")
    protected Boolean matchesProvidedName;

    @JsonProperty("phone_number")
    protected String phoneNumber;

    @JsonProperty("matches_provided_phone_number")
    protected Boolean matchesProvidedPhoneNumber;

    public final String getName() {
        return name;
    }

    public final Boolean matchesProvidedName() {
        return matchesProvidedName;
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final Boolean matchesProvidedPhoneNumber() {
        return matchesProvidedPhoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Issuer{");
        sb.append("name='").append(this.name).append('\'');
        sb.append(", matchesProvidedName=").append(this.matchesProvidedName);
        sb.append(", phoneNumber='").append(this.phoneNumber).append('\'');
        sb.append(", matchesProvidedPhoneNumber=").append(this.matchesProvidedPhoneNumber);
        sb.append('}');
        return sb.toString();
    }
}
