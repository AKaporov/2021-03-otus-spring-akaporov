package ru.otus.spring.service.exception;

public class EncodePasswordException extends RuntimeException {
    public EncodePasswordException(String errorMessage) {
        super(errorMessage);
    }
}
