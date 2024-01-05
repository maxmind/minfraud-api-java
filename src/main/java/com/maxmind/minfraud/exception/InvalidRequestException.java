package com.maxmind.minfraud.exception;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * This class represents a non-specific error returned by MaxMind's minFraud web
 * service. This occurs when the web service is up and responding to requests,
 * but the request sent was invalid in some way.
 */
public class InvalidRequestException extends MinFraudException {
    private final String code;
    private final int httpStatus;
    private final URI uri;

    /**
     * @param message A message explaining the cause of the error.
     * @param code    The error code returned by the web service.
     * @param uri     The URL queried.
     */
    public InvalidRequestException(String message, String code, URI uri) {
        super(message);
        this.uri = uri;
        this.code = code;
        this.httpStatus = 0;
    }

    /**
     * @param message    A message explaining the cause of the error.
     * @param code       The error code returned by the web service.
     * @param httpStatus The HTTP status of the response.
     * @param uri        The URL queried.
     * @param e          The cause of the exception.
     */
    public InvalidRequestException(String message, String code, int httpStatus,
                                   URI uri, Throwable e) {
        super(message, e);
        this.code = code;
        this.uri = uri;
        this.httpStatus = httpStatus;
    }

    /**
     * @return The error code returned by the MaxMind web service.
     */
    public final String getCode() {
        return code;
    }

    /**
     * @return The integer HTTP status returned by the MaxMind web service. Will be 0 if
     * it was not set at throw time.
     */
    public final int getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the URI queried.
     */
    public URI getUri() {
        return this.uri;
    }

    /**
     * @return the URL queried.
     * @deprecated Use getUri() instead
     */
    @Deprecated
    public URL getUrl() {
        try {
            return this.uri.toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
