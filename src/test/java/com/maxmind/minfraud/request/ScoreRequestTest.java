package com.maxmind.minfraud.request;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ScoreRequestTest extends AbstractRequestTest {
    ScoreRequest.Builder builder() throws UnknownHostException {
        return new ScoreRequest.Builder(new Device.Builder(InetAddress.getByName("81.2.69.160")).build());
    }
}
