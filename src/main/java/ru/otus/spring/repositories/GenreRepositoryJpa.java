package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
