package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Класс Вопрос
 */

@Getter
@RequiredArgsConstructor
public class Question {
    private final String rightAnswer;
    private final String question;
    private final List<String> answer;
}
