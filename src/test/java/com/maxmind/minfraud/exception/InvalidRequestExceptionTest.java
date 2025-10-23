package com.maxmind.minfraud.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import org.junit.jupiter.api.Test;

public class InvalidRequestExceptionTest {

    @Test
    public void testInvalidRequestException() throws Exception {
        URI uri = new URI("https://www.maxmind.com/");
        String code = "INVALID_INPUT";
        int status = 400;
        InvalidRequestException e = new InvalidRequestException("message", code, status, uri, null);
        assertEquals(code, e.code(), "correct code");
        assertEquals(status, e.httpStatus(), "correct status");
        assertEquals(uri, e.uri(), "correct URL");
    }
}
