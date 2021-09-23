package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.data=genre-test.sql"})
@DisplayName("Репозиторий на основе Jpa по работе с Жанрами")
class GenreRepositoryJpaImplTest {
    private static final long GENRE_EXISTS_ID = 1;
    @Autowired
    private GenreRepositoryJpa repositoryJpa;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен находить Жанр по его name")
    void shouldFindGenreByName() {
        Genre expectedGenre = em.find(Genre.class, GENRE_EXISTS_ID);

        Optional<Genre> actualGenre = repositoryJpa.findByName(expectedGenre.getName());

        assertThat(actualGenre).isNotNull().isPresent().get().usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}