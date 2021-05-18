package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long insert(Book book);

    Optional<Book> getById(long id);

    Optional<Book> getByTitle(String titleBook);

    List<Book> getAll();

    void update(Book book);

    void deleteById(long id);
}
