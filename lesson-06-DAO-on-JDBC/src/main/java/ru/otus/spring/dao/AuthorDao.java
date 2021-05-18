package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    long insert(Author author);

    Optional<Author> getById(long id);

    Optional<Author> getByFullName(Author author);

    List<Author> getAll();

    void update(Author author);

    void deleteById(long id);
}
