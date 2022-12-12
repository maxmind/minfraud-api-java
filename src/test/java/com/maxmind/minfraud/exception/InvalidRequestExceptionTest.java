package com.maxmind.minfraud.exception;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import org.junit.Test;

public class InvalidRequestExceptionTest {

    @Test
    public void testInvalidRequestException() throws Exception {
        URI uri = new URI("https://www.maxmind.com/");
        String code = "INVALID_INPUT";
        int status = 400;
        InvalidRequestException e = new InvalidRequestException("message", code, status, uri, null);
        assertEquals("correct code", code, e.getCode());
        assertEquals("correct status", status, e.getHttpStatus());
        assertEquals("correct URL", uri, e.getUri());
    }
}
