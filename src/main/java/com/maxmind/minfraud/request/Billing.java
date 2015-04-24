package com.maxmind.minfraud.request;

/**
 * The billing information for the transaction.
 */
public class Billing extends AbstractLocation {
    private Billing(Billing.Builder builder) {
        super(builder);
    }

    public static final class Builder extends AbstractLocation.Builder<Billing.Builder> {
        public Billing build() {
            return new Billing(this);
        }
    }
}