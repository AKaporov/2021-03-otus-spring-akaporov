package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Жанр книги
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Genre {
    private long id;
    private final String name;
}
