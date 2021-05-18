package ru.otus.spring.service;

import ru.otus.spring.domain.BookToAuthorToGenreLink;

import java.util.List;

public interface BookToAuthorToGenreLinkService {
    BookToAuthorToGenreLink getByBookTitle(String bookTitle);

    long insert(BookToAuthorToGenreLink link);

    void deleteById(long id);

    List<BookToAuthorToGenreLink> getAllLink();
}
