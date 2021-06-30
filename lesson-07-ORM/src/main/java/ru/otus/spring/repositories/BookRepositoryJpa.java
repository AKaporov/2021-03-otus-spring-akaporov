package ru.otus.spring.repositories;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    Book save(Book book);

    void remove(Book book);

    Optional<Book> findByTitle(String title);

    List<Book> findAll();
}
