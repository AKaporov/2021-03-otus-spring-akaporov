package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.exception.DaoException;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("Dao для работы с Author")
class AuthorDaoJdbcTest {
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy A. N.";
    private static final long NEW_AUTHOR_ID = 2L;
    private static final String NEW_AUTHOR_NAME = "Uspensky E. N.";

    @Autowired
    private AuthorDaoJdbc dao;

    @Test
    @DisplayName(" дожен добовлять author в БД")
    void shouldInsertNewAuthor() {
        Author expectedAuthor = new Author(NEW_AUTHOR_ID, NEW_AUTHOR_NAME);

        Author insertAuthor = dao.insert(new Author(NEW_AUTHOR_NAME));

        Author actualAuthor = dao.getById(insertAuthor.getId());

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName(" дожен возвращать ожидаемого author по его id")
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        Author actualAuthor = dao.getById(EXISTING_AUTHOR_ID);

        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName(" должен возвращать всех author из БД")
    void shouldReturnAllAuthors() {
        Author expectedAuthors = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        List<Author> actualAuthorsList = dao.getAll();

        assertThat(actualAuthorsList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedAuthors);
    }

    @Test
    @DisplayName(" должен изменять существующего author")
    void shouldUpdateExistAuthor() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, NEW_AUTHOR_NAME);

        dao.update(expectedAuthor);

        Author actualAuthor = dao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName(" должен удалять author из БД по Id")
    void shouldDeleteAuthorById() {
        assertThatCode(() -> dao.getById(EXISTING_AUTHOR_ID)).doesNotThrowAnyException();

        dao.deleteById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> dao.getById(EXISTING_AUTHOR_ID)).isInstanceOf(DaoException.class);
    }

    @Test
    @DisplayName("должен возвращать author по полному имени")
    void shouldReturnExpectedAuthorByFullName() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        Optional<Author> actualAuthor = dao.getByName(EXISTING_AUTHOR_NAME);

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(Optional.of(expectedAuthor));
    }

    @Test
    @DisplayName(" должен кидать DaoException, если не нашел author по id")
    void shouldReturnDaoExceptionByNotExistsAuthorId() {
        assertThatThrownBy(() -> dao.getById(NEW_AUTHOR_ID)).isInstanceOf(DaoException.class);
    }
}