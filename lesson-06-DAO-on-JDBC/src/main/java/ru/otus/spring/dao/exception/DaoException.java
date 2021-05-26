package ru.otus.spring.dao.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DaoException extends RuntimeException {
    private final String message;
}
