package ru.otus.spring.repositories;

import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    Optional<Genre> findByName(String name);

    List<Genre> findAll();

    void deleteById(long id);    
}
