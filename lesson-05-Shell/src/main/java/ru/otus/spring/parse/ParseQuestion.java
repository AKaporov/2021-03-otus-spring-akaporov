package ru.otus.spring.parse;

import ru.otus.spring.domain.Question;

import java.util.List;

/**
 * Интерфейс для преобразований списка массива строк в список строк.
 */
public interface ParseQuestion {
    List<Question> parser(List<String[]> arrayList);
}
