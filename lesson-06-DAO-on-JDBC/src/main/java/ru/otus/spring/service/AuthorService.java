package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    long insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void update(Author author);

    void deleteById(long id);

    Author getByFullName(Author author);

    Author parserFullName(String fullNameAuthor);
}
