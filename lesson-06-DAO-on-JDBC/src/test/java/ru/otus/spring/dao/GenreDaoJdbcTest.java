package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(GenreDaoJdbc.class)
@DisplayName("Dao для работы с Genre")
class GenreDaoJdbcTest {

    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Story";
    private static final String NEW_GENRE_NAME = "Comedy";
    private static final long NEW_GENRE_ID = 2L;
    @Autowired
    private GenreDaoJdbc dao;

    @Test
    @DisplayName("должен добавлять нового genre в БД")
    void shouldInsertNewGenre() {
        Genre expectedGenre = new Genre(NEW_GENRE_ID, NEW_GENRE_NAME);

        Genre insertGenre = new Genre(NEW_GENRE_NAME);
        long newGenreId = dao.insert(insertGenre);

        Optional<Genre> actualGenre = dao.getById(newGenreId);

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(Optional.of(expectedGenre));
    }

    @Test
    @DisplayName("должен возвращать ожидаемый genre по его id")
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);

        Optional<Genre> actualGenre = dao.getById(expectedGenre.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(Optional.of(expectedGenre));
    }

    @Test
    @DisplayName("должен возвращать ожидаемый списко genre")
    void shouldReturnExpectedGenreList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);

        List<Genre> actualGenresList = dao.getAll();

        assertThat(actualGenresList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedGenre);
    }

    @Test
    @DisplayName("должен изменять genre по его id")
    void shouldUpdateGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, NEW_GENRE_NAME);

        dao.update(expectedGenre);
        Optional<Genre> actualGenre = dao.getById(EXISTING_GENRE_ID);

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(Optional.of(expectedGenre));
    }

    @Test
    @DisplayName("должен возвращать ожидаемый genre по name")
    void shouldReturnExpectedGenreByName() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);

        Optional<Genre> actualGenre = dao.getByName(EXISTING_GENRE_NAME);

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(Optional.of(expectedGenre));
    }
}