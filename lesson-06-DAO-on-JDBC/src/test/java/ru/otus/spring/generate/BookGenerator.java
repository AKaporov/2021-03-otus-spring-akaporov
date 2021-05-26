package ru.otus.spring.generate;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public class BookGenerator {
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy A. N.";
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Story";

    public static Book createNewBook(long bookId, String bookTitle) {
        return new Book(bookId, bookTitle, new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME), new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
    }

    public static Book createNewBook(String bookTitle) {
        return new Book(bookTitle, new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME), new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
    }
}
