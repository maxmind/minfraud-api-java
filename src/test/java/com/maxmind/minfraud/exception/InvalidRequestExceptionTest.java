package com.maxmind.minfraud.exception;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class InvalidRequestExceptionTest {

    @Test
    public void testInvalidRequestException() throws Exception {
        URL url = new URL("https://www.maxmind.com/");
        String code = "INVALID_INPUT";
        int status = 400;
        InvalidRequestException e = new InvalidRequestException("message", code, status, url, null);
        assertEquals("correct code", code, e.getCode());
        assertEquals("correct status", status, e.getHttpStatus());
        assertEquals("correct URL", url, e.getUrl());
    }
}
