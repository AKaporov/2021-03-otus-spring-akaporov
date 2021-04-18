package ru.otus.spring.parser;

import ru.otus.spring.domain.Question;

import java.util.List;

/**
 * Интерфейс для преобразований списка массива строк в список строк.
 */
public interface ParserQuestion {
    List<Question> parser(List<String[]> arrayList);
}
