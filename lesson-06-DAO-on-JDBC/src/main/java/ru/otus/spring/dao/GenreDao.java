package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Genre insert(Genre genre);

    Genre getById(long id);

    Optional<Genre> getByName(String genreName);

    List<Genre> getAll();

    void update(Genre genre);

    void deleteById(long id);
}
