package ru.otus.spring.dao;

import ru.otus.spring.domain.BookToAuthorToGenreLink;

import java.util.List;
import java.util.Optional;

public interface BookToAuthorToGenreLinkDao {
    Optional<BookToAuthorToGenreLink> getByBookTitle(String bootTitle);

    long insert(BookToAuthorToGenreLink link);

    Optional<BookToAuthorToGenreLink> getById(long id);

    void deleteById(long id);

    List<BookToAuthorToGenreLink> getAllLink();
}
