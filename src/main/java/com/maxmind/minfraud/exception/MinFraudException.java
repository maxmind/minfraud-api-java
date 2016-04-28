package com.maxmind.minfraud.exception;

/**
 * This class represents a non-specific error with the web service.
 * Generally this will be thrown if the web service responds with an
 * expected status but unexpected content.
 * <p>
 * It also serves as the base class for {@code AuthenticationException},
 * {@code InsufficientFundsException}, and {@code InvalidRequestException}.
 */
public class MinFraudException extends Exception {

    /**
     * @param message A message explaining the cause of the error.
     */
    public MinFraudException(String message) {
        super(message);
    }

    /**
     * @param message A message explaining the cause of the error.
     * @param e       The cause of the exception.
     */
    public MinFraudException(String message, Throwable e) {
        super(message, e);
    }
}
