package com.maxmind.minfraud.exception;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class HttpExceptionTest {

    @Test
    public void testHttpException() throws Exception {
        URL url = new URL("https://www.maxmind.com/");
        HttpException e = new HttpException("message", 200, url);
        assertEquals("correct status", 200, e.getHttpStatus());
        assertEquals("correct URL", url, e.getUrl());
    }
}