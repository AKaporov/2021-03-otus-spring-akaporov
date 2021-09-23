package ru.otus.spring.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookNotFoundException extends RuntimeException {
    private final String errorMessage;
}
