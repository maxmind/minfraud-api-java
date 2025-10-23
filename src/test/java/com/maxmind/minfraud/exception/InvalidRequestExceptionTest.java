package com.maxmind.minfraud.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import org.junit.jupiter.api.Test;

public class InvalidRequestExceptionTest {

    @Test
    public void testInvalidRequestException() throws Exception {
        var uri = new URI("https://www.maxmind.com/");
        var code = "INVALID_INPUT";
        var status = 400;
        var e = new InvalidRequestException("message", code, status, uri, null);
        assertEquals(code, e.code(), "correct code");
        assertEquals(status, e.httpStatus(), "correct status");
        assertEquals(uri, e.uri(), "correct URL");
    }
}
