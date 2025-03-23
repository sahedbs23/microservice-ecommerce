package com.kids.collection.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Long recordId) {
        this("Database record not found with id: " + recordId);
    }
}
