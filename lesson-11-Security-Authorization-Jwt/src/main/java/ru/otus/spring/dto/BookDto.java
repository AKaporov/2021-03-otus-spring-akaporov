package ru.otus.spring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookDto {
    private final String title;
    private final String author;
    private final String genre;

    @Override
    public String toString() {
        return "BookDto{title = " + this.getTitle() +
                ", author_name = " + this.getAuthor() +
                ", genre_name = " + this.getGenre() +
                "}";
    }
}
