package com.maxmind.minfraud.exception;

/**
 * This exception is thrown when permission is required to use the service.
 */
public final class PermissionRequiredException extends MinFraudException {
    /**
     * @param message A message explaining the cause of the error.
     */
    public PermissionRequiredException(String message) {
        super(message);
    }

    /**
     * @param message A message explaining the cause of the error.
     * @param e       The cause of the exception.
     */
    public PermissionRequiredException(String message, Throwable e) {
        super(message, e);
    }
}