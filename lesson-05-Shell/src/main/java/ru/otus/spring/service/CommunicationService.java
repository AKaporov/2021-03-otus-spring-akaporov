package ru.otus.spring.service;

/**
 * Интерфейс для общения с тестируемым
 */
public interface CommunicationService {
    /**
     * Задать вопрос {@code String}
     */
    void ask(String question);

    /**
     * Получить ответ тестируемого на вопрос.
     *
     * @return ответ {@code String} тестируемого на вопрос.
     */
    String getAnswer();
}
