package com.sahaj.metroPaymentSystem.Exceptions;

public class TigerCardException extends Exception {

    public TigerCardException(String message) {
        super(message);
    }

    public TigerCardException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
