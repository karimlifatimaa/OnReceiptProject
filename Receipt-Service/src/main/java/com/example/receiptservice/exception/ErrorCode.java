package com.example.receiptservice.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    RECEIPT_NOT_FOUND("Receipt not found", HttpStatus.NOT_FOUND),
    DRUG_NOT_FOUND("Drug not found", HttpStatus.NOT_FOUND),
    RECEIPT_ITEM_NOT_FOUND("Receipt item not found", HttpStatus.NOT_FOUND),
    DUPLICATE_SERIAL("Duplicate serial number", HttpStatus.BAD_REQUEST),
    BAD_REQUEST("Invalid request", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}