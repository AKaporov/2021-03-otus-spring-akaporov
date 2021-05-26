package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void update(Genre genre);

    void deleteById(long id);

    Optional<Genre> getByName(String genreName);
}
