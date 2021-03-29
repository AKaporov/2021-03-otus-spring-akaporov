package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.QuestionDao;

import java.io.IOException;

/**
 * Реализация интерфейса по проведению тестирования студентов.
 */

@RequiredArgsConstructor
public class TestingStudentsImpl implements TestingStudents {
    private static final int COUNT_QUESTION = 5;

    private final QuestionDao questions;

    /**
     * Начало тестирования.
     *
     * @throws IOException в случее возникновения исключительной ошибки.
     */
    @Override
    public void startTesting() throws IOException {
        for (int i = 0; i < COUNT_QUESTION; i++) {
            String question = this.questions.getQuestion(i);
            System.out.println(question);
        }
    }
}
