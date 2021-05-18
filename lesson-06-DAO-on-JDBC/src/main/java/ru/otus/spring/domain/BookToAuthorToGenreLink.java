package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Связь книга - автор - жанр
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BookToAuthorToGenreLink {
    private long id;
    private final Book book;
    private final Author author;
    private final Genre genre;

    @Override
    public String toString() {
        return String.format("%n\"%s\" \"%s %s %s\" \"%s\"",
                this.getBook().getTitle(),
                this.getAuthor().getSurName(), this.getAuthor().getName(), this.getAuthor().getPatronymic(),
                this.getGenre().getName());
    }
}
