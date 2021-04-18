package ru.otus.spring.parser;

import java.io.InputStream;
import java.util.List;

/**
 * Интерфейс для преобразований потока в список массивов строк.
 */
public interface ParserInputStream {
    List<String[]> parser(InputStream stream);
}