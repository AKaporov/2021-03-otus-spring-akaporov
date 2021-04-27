package ru.otus.spring.parse.exception;

import lombok.RequiredArgsConstructor;

/**
 * Исключение при преобрабразовании потока в список вопросов
 */

@RequiredArgsConstructor
public class ParseException extends RuntimeException {
    private final String message;
}
