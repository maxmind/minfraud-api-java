package com.maxmind.minfraud.request;

import java.io.IOException;

public interface RequestInterface {
    String toJson() throws IOException;
}
