package ru.otus.spring.parser.exception;

import lombok.RequiredArgsConstructor;

/**
 * Исключение при преобрабразовании потока в список вопросов
 */

@RequiredArgsConstructor
public class ParserException extends RuntimeException {
    private final String message;
}
