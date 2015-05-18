package com.maxmind.minfraud.request;

import java.io.IOException;

/**
 * A shared interface for the request classes.
 */
public interface RequestInterface {
    String toJson() throws IOException;
}
