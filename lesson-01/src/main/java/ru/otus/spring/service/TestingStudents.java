package ru.otus.spring.service;

import java.io.IOException;

/**
 * Интерфейс по проведению тестиривания студентов.
 */
public interface TestingStudents {
    /**
     * Начало тестирования.
     *
     * @throws IOException в случее возникновения исключительной ошибки.
     */
    void startTesting() throws IOException;
}
