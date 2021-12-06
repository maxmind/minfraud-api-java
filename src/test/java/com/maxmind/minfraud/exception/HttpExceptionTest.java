package com.maxmind.minfraud.exception;

import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class HttpExceptionTest {

    @Test
    public void testHttpException() throws Exception {
        URI uri = new URI("https://www.maxmind.com/");
        HttpException e = new HttpException("message", 200, uri);
        assertEquals("correct status", 200, e.getHttpStatus());
        assertEquals("correct URL", uri, e.getUri());
    }
}