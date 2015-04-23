package com.maxmind.minfraud.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Warning {
    protected String code;
    protected String warning;
    protected List<String> input;

    public String getCode() {
        return code;
    }

    public String getWarning() {
        return warning;
    }

    public List<String> getInput() {
        return new ArrayList<>(input);
    }

    @Override
    public String toString() {
        return "Warning{" +
                "code='" + code + '\'' +
                ", warning='" + warning + '\'' +
                ", input=" + input +
                '}';
    }
}
