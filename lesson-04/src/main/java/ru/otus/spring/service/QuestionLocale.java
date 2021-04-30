package ru.otus.spring.service;

import org.springframework.lang.Nullable;

/**
 * Класс для конфигурации сервиса локализации
 */

public interface QuestionLocale {
    String getMessage(String bundleName, @Nullable Object[] args);
}
