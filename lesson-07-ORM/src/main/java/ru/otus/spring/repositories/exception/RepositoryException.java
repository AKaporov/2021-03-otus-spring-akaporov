package ru.otus.spring.repositories.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RepositoryException extends RuntimeException {
    private final String message;
}
