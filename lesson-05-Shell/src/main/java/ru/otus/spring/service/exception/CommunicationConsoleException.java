package ru.otus.spring.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Исключение при получении ответа во время общения
 */

@Getter
@RequiredArgsConstructor
public class CommunicationConsoleException extends RuntimeException {
    private final String message;
}
