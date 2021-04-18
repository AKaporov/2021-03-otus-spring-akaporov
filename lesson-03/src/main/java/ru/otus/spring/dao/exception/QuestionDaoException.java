package ru.otus.spring.dao.exception;

import lombok.RequiredArgsConstructor;

/**
 * Исключение, которое может возникнуть при получении вопросов
 */

@RequiredArgsConstructor
public class QuestionDaoException extends RuntimeException {
    private final String message;
}
