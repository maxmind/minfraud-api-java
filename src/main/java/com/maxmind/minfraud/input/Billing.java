package com.maxmind.minfraud.input;

/**
 * The billing information for the transaction.
 */
public class Billing extends AbstractLocation {
    private Billing(Builder builder) {
        super(builder);
    }

    public final static class Builder extends AbstractLocation.Builder<Builder> {
        public Billing build() {
            return new Billing(this);
        }
    }
}