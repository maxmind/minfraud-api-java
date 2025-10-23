package com.maxmind.minfraud.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import org.junit.jupiter.api.Test;

public class HttpExceptionTest {

    @Test
    public void testHttpException() throws Exception {
        URI uri = new URI("https://www.maxmind.com/");
        HttpException e = new HttpException("message", 200, uri);
        assertEquals(200, e.httpStatus(), "correct status");
        assertEquals(uri, e.uri(), "correct URL");
    }
}