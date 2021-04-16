package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.List;

/**
 * Интерфейс для работы с Dao вопросами
 */

public interface QuestionDao {
    List<Question> getQuestion();
}
