package ru.otus.spring.dao;

import java.io.IOException;

/**
 * Интерфейс получения вопросов
 */
public interface QuestionDao {
    /**
     * Возвращает вопрос из файла, по указанному номеру {@code numberQuestion}.
     *
     * @param numberQuestion номер вопроса в файле.
     * @return вопрос в файле
     * @throws IOException если файл не найден.
     */
    String getQuestion(int numberQuestion) throws IOException;
}
