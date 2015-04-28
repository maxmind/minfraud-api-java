package com.maxmind.minfraud.request;

public class InsightsRequest extends AbstractRequest {
    private InsightsRequest(InsightsRequest.Builder builder) {
        super(builder);
    }

    public static final class Builder extends AbstractRequest.Builder<InsightsRequest.Builder> {
        public Builder(Device device) {
            super(device);
        }

        public InsightsRequest build() {
            return new InsightsRequest(this);
        }
    }
}
