package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.data=genre-test.sql"})
@Import(GenreRepositoryJpaImpl.class)
@DisplayName("Репозиторий на основе Jpa по работе с Жанрами")
class GenreRepositoryJpaImplTest {

    private static final long GENRE_EXISTS_ID = 1;
    private static final int GENRE_EXISTS_COUNT = 2;
    private static final String GENRE_NEW_NAME = "Comedy";
    private static final long GENRE_EMPTY_ID = 0;
    @Autowired
    private GenreRepositoryJpaImpl repositoryJpa;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен добавлять новый Жанр")
    void shouldSaveNewGenre() {
        Genre expectedGenre = new Genre(GENRE_EMPTY_ID, GENRE_NEW_NAME);

        repositoryJpa.save(expectedGenre);
        assertThat(expectedGenre.getId()).isGreaterThan(GENRE_EMPTY_ID);

        Genre actualGenre = em.find(Genre.class, expectedGenre.getId());
        assertThat(actualGenre).isNotNull().matches(g -> GENRE_NEW_NAME.equalsIgnoreCase(g.getName()));
    }

    @Test
    @DisplayName("должен находить Жанр по его id")
    void shouldFindGenreById() {
        Genre expectedGenre = em.find(Genre.class, GENRE_EXISTS_ID);

        Optional<Genre> actualGenre = repositoryJpa.findById(GENRE_EXISTS_ID);

        assertThat(actualGenre).isNotNull().isPresent().get().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("должен находить Жанр по его name")
    void shouldFindGenreByName() {
        Genre expectedGenre = em.find(Genre.class, GENRE_EXISTS_ID);

        Optional<Genre> actualGenre = repositoryJpa.findByName(expectedGenre.getName());

        assertThat(actualGenre).isNotNull().isPresent().get().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("должен находить все Жанры")
    void shouldFindAllGenre() {
        List<Genre> actualGenre = repositoryJpa.findAll();

        assertThat(actualGenre).isNotNull().isNotEmpty().hasSize(GENRE_EXISTS_COUNT)
                .allMatch(g -> !"".equalsIgnoreCase(g.getName()))
                .allMatch(g -> g.getId() > 0);
    }

    @Test
    @DisplayName("должен удалять Жанр по его id")
    void shouldDeleteGenreById() {
        Genre expectedGenre = em.find(Genre.class, GENRE_EXISTS_ID);
        assertThat(expectedGenre).isNotNull();
        em.detach(expectedGenre);

        repositoryJpa.deleteById(expectedGenre.getId());

        Genre actualGenre = em.find(Genre.class, expectedGenre.getId());

        em.getEntityManager().getReference(Genre.class, expectedGenre.getId());

        assertThat(actualGenre).isNull();
    }

    @Test
    @DisplayName("должен изменять заданный Жарн по его id")
    void shouldUpdateAuthor() {
        Genre expectedGenre = new Genre(GENRE_EXISTS_ID, GENRE_NEW_NAME);

        Genre foundGenre = em.find(Genre.class, expectedGenre.getId());
        String oldName = foundGenre.getName();
        em.detach(foundGenre);

        repositoryJpa.save(expectedGenre);

        Genre actualGenre = em.find(Genre.class, expectedGenre.getId());

        assertThat(actualGenre.getName()).isNotNull().isNotEmpty().isNotEqualTo(oldName).isEqualTo(expectedGenre.getName());
    }
}