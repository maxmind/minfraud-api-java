package com.maxmind.minfraud.request;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class InsightsRequestTest extends AbstractRequestTest {
    InsightsRequest.Builder builder() throws UnknownHostException {
        return new InsightsRequest.Builder(new Device.Builder(InetAddress.getByName("81.2.69.160")).build());
    }
}
