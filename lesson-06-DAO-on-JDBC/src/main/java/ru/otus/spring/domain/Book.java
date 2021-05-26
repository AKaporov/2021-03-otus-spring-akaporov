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

    private final Author author;
    private final Genre genre;

    @Override
    public String toString() {
        return String.format("%n\"%s\" \"%s\" \"%s\"",
                this.getTitle(), this.getAuthor().getName(), this.getGenre().getName());
    }
}
