package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    long insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void update(Genre genre);

    void deleteById(long id);

    Genre getByName(String genreName);
}
