package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

/**
 * Интерфейс по работе с вопросами
 */
public interface QuestionService {
    List<Question> getAllQuestion();

    void askQuestion(Question question);
}
