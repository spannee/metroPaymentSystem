package com.sahaj.metroPaymentSystem.enums;

public enum ErrorMessages {

    INVALID_DAY("Invalid day given for the trip"),
    INVALID_ZONE_ID("Invalid Zone Id given"),
    INVALID_INPUT("The provided input is invalid");

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
