package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Автор книги
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Author {
    private long id;
    private final String name;
}
