package ru.otus.spring.dao;

import java.util.List;

/**
 * Интерфейс для работы с ресурсами
 */

public interface QuestionDao {
    List<String[]> getQuestion();
}
