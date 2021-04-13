package ru.otus.spring.service;

import java.io.IOException;

/**
 * Интерфейс по проведению тестиривания студентов.
 */
public interface StudentTestService {
    /**
     * Начало тестирования.
     *
     * @throws IOException в случее возникновения исключительной ошибки.
     */
    void startTest();
}
