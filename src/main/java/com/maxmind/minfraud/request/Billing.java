package com.maxmind.minfraud.request;

/**
 * The billing information for the transaction.
 */
public final class Billing extends AbstractLocation {
    private Billing(Billing.Builder builder) {
        super(builder);
    }

    /**
     * {@code Builder} creates instances of {@code Billing}
     * from values set by the builder's methods.
     */
    public static final class Builder extends AbstractLocation.Builder<Billing.Builder> {
        /**
         * @return An instance of {@code Billing} created from the
         * fields set on this builder.
         */
        @Override
        public Billing build() {
            return new Billing(this);
        }
    }
}