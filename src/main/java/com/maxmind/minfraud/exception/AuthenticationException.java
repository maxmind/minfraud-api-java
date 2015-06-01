package com.maxmind.minfraud.exception;

/**
 * This exception is thrown when there is an error authenticating.
 */
public final class AuthenticationException extends MinFraudException {
    /**
     * @param message A message explaining the cause of the error.
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * @param message A message explaining the cause of the error.
     * @param e       The cause of the exception.
     */
    public AuthenticationException(String message, Throwable e) {
        super(message, e);
    }
}