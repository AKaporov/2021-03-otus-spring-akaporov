package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

/**
 * Интерфейс по проведению тестиривания студентов.
 */
public interface StudentTestService {
    void startTest(Student student);
}
