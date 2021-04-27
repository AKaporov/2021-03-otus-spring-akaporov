package ru.otus.spring.parse;

import java.io.InputStream;
import java.util.List;

/**
 * Интерфейс для преобразований потока в список массивов строк.
 */
public interface ParseInputStream {
    List<String[]> parser(InputStream stream);
}