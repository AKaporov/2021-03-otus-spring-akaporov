package ru.otus.spring.dao;

import java.util.List;

/**
 * Интерфейс для работы с Dao вопросами
 */

public interface QuestionDao {
    List<String[]> getQuestion();
}
