package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс результата тестирования студентов
 */

@Getter
@RequiredArgsConstructor
public class ResultStudentTest {
    private final Student student;
    private final int countQuestion;
    private final int countRightAnswer;
}
