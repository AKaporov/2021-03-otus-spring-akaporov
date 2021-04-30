package ru.otus.spring.dao.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Исключение, которое может возникнуть при получении вопросов
 */

@Getter
@RequiredArgsConstructor
public class QuestionDaoException extends RuntimeException {
    private final String message;
}
