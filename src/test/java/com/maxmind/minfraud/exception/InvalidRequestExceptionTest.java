package com.maxmind.minfraud.exception;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class InvalidRequestExceptionTest {

    @Test
    public void testInvalidRequestException() throws Exception {
        URL url = new URL("https://www.maxmind.com/");
        String code = "INVALID_INPUT";
        InvalidRequestException e = new InvalidRequestException("message", code, url);
        assertEquals("correct code", code, e.getCode());
        assertEquals("correct URL", url, e.getUrl());
    }
}