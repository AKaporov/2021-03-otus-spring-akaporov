package ru.otus.spring.service.exception;

import lombok.RequiredArgsConstructor;

/**
 * Исключение при получении ответа во время общения
 */

@RequiredArgsConstructor
public class CommunicationConsoleException extends RuntimeException {
    private final String message;
}
