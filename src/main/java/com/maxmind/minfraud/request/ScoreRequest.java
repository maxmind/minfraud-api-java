package com.maxmind.minfraud.request;

public final class ScoreRequest extends AbstractRequest {
    private ScoreRequest(ScoreRequest.Builder builder) {
        super(builder);
    }

    /**
     * {@code Builder} creates instances of {@code ScoreRequest}
     * from values set by the builder's methods.
     */
    public static final class Builder extends AbstractRequest.Builder<ScoreRequest.Builder> {
        public Builder(Device device) {
            super(device);
        }

        /**
         * @return An instance of {@code ScoreRequest} created from the
         * fields set on this builder.
         */
        public ScoreRequest build() {
            return new ScoreRequest(this);
        }
    }
}
