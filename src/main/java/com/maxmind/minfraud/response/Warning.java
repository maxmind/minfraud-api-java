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
    public String toString() {
        final StringBuilder sb = new StringBuilder("Warning{");
        sb.append("code='").append(code).append('\'');
        sb.append(", warning='").append(warning).append('\'');
        sb.append(", input=").append(input);
        sb.append('}');
        return sb.toString();
    }
}
