package com.maxmind.minfraud.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
