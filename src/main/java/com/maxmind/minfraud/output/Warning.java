package com.maxmind.minfraud.output;

import java.util.Arrays;

public class Warning {
    protected String code;
    protected String warning;
    protected String[] input;

    public String getCode() {
        return this.code;
    }

    public String getWarning() {
        return this.warning;
    }

    public String[] getInput() {
        return this.input;
    }

    @Override
    public String toString() {
        return "Warning{" +
                "code='" + code + '\'' +
                ", warning='" + warning + '\'' +
                ", input=" + Arrays.toString(input) +
                '}';
    }
}
