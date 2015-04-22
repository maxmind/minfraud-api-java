package com.maxmind.minfraud.exception;

/**
 * Created by greg on 4/20/15.
 */
public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
