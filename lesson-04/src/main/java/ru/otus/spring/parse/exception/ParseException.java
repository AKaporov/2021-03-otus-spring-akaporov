package ru.otus.spring.parse.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Исключение при преобрабразовании потока в список вопросов
 */

@Getter
@RequiredArgsConstructor
public class ParseException extends RuntimeException {
    private final String message;
}
