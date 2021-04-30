package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс Студента которого тестируют
 */

@Getter
@RequiredArgsConstructor
public class Student {
    private final String name;
    private final String surname;
}
