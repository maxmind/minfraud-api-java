package com.maxmind.minfraud.exception;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import org.junit.Test;

public class HttpExceptionTest {

    @Test
    public void testHttpException() throws Exception {
        URI uri = new URI("https://www.maxmind.com/");
        HttpException e = new HttpException("message", 200, uri);
        assertEquals("correct status", 200, e.getHttpStatus());
        assertEquals("correct URL", uri, e.getUri());
    }
}