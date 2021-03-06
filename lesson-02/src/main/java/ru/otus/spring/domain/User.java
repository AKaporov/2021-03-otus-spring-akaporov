package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Класс Пользователя которого тестируют
 */

@Getter
@RequiredArgsConstructor
public class User {
    private final String name;
    private final String surname;
}
