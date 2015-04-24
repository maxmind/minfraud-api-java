package com.maxmind.minfraud.response;

import java.util.ArrayList;
import java.util.List;

public class Warning {
    protected String code;
    protected String warning;
    protected List<String> input;

    public final String getCode() {
        return this.code;
    }

    public final String getWarning() {
        return this.warning;
    }

    public final List<String> getInput() {
        return new ArrayList<>(this.input);
    }

    @Override
    public final String toString() {
        return "Warning{" +
                "code='" + this.code + '\'' +
                ", warning='" + this.warning + '\'' +
                ", request=" + this.input +
                '}';
    }
}
