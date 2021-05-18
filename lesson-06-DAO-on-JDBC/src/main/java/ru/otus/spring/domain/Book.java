package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Книга
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;
    private final String title;
}
