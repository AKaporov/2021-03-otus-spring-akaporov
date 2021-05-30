package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book insertAll(Book book);

    Book getAllById(long id);

    Book getById(long id);

    Optional<Book> getAllByTitle(String bookTitle);

    List<Book> getAll();

    void update(Book book);

    void deleteById(long id);


}
