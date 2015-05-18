package com.maxmind.minfraud.request;

public final class InsightsRequest extends AbstractRequest {
    private InsightsRequest(InsightsRequest.Builder builder) {
        super(builder);
    }

    /**
     * {@code Builder} creates instances of {@code InsightsRequest}
     * from values set by the builder's methods.
     */
    public static final class Builder extends AbstractRequest.Builder<InsightsRequest.Builder> {
        public Builder(Device device) {
            super(device);
        }

        /**
         * @return An instance of {@code InsightsRequest} created from the
         * fields set on this builder.
         */
        public InsightsRequest build() {
            return new InsightsRequest(this);
        }
    }
}
