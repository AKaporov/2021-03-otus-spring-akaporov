package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book insertAll(Book book);

    Book getAllById(long id);

    Book getById(long id);

    Optional<Book> getAllByTitle(String bookTitle);

    Optional<Book> getByTitle(String titleBook);

    List<Book> getAll();

    void update(Book book);

    void deleteById(long id);
}
