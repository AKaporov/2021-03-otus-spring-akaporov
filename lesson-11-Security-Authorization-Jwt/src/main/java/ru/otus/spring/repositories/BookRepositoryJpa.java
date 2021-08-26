package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"genre", "author"})
    Optional<Book> findByTitle(String title);

    @EntityGraph(attributePaths = {"genre", "author"})
    @Override
    List<Book> findAll();
}
