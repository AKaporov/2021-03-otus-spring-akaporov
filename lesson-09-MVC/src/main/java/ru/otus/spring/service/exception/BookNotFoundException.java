package ru.otus.spring.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookNotFoundException extends RuntimeException {
    private final String errorMessage;

    public String getFullTextError() {
        return String.format("The book {%s} was not found. Check the request details", this.errorMessage);
    }
}
