package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author insert(Author author);

    Author getById(long id);

    Optional<Author> getByName(String name);

    List<Author> getAll();

    void update(Author author);

    void deleteById(long id);
}
