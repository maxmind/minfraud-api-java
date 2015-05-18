package com.maxmind.minfraud.exception;

/**
 * This exception is thrown when your account does not have sufficient funds
 * to complete the request.
 */
public class InsufficientFundsException extends MinFraudException {

    /**
     * @param message A message explaining the cause of the error.
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
