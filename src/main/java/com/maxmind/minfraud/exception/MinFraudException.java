package com.maxmind.minfraud.exception;


public class MinFraudException extends Exception {
    public MinFraudException(String msg) {
        super(msg);
    }

    public MinFraudException(String msg, Throwable e) {
        super(msg, e);
    }
}