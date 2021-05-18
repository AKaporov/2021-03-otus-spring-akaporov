package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("Dao для работы с Author")
class AuthorDaoJdbcTest {
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_SURNAME = "Tolstoy";
    private static final String EXISTING_AUTHOR_NAME = "Alexey";
    private static final String EXISTING_AUTHOR_PATRONYMIC = "Nikolaevich";
    private static final long NEW_AUTHOR_ID = 2L;
    private static final String NEW_AUTHOR_SURNAME = "Lagin";
    private static final String NEW_AUTHOR_NAME = "Lazar";
    private static final String NEW_AUTHOR_PATRONYMIC = "Iosifovich";

    @Autowired
    private AuthorDaoJdbc dao;

    @Test
    @DisplayName(" дожен добовлять author в БД")
    void shouldInsertNewAuthor() {
        Author expectedAuthor = new Author(NEW_AUTHOR_ID, NEW_AUTHOR_SURNAME, NEW_AUTHOR_NAME, NEW_AUTHOR_PATRONYMIC);

        Author insertAuthor = new Author(NEW_AUTHOR_SURNAME, NEW_AUTHOR_NAME, NEW_AUTHOR_PATRONYMIC);
        long insertNewAuthorId = dao.insert(insertAuthor);

        Optional<Author> actualAuthor = dao.getById(insertNewAuthorId);

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(Optional.of(expectedAuthor));
    }

    @Test
    @DisplayName(" дожен возвращать ожидаемого author по его id")
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_SURNAME, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_PATRONYMIC);

        Optional<Author> actualAuthor = dao.getById(EXISTING_AUTHOR_ID);

        assertThat(actualAuthor).isEqualTo(Optional.of(expectedAuthor));
    }

    @Test
    @DisplayName(" должен возвращать всех author из БД")
    void shouldReturnAllAuthors() {
        Author expectedAuthors = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_SURNAME, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_PATRONYMIC);

        List<Author> actualAuthorsList = dao.getAll();

        assertThat(actualAuthorsList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedAuthors);
    }

    @Test
    @DisplayName(" должен изменять существующего author")
    void shouldUpdateExistAuthor() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, NEW_AUTHOR_SURNAME, NEW_AUTHOR_NAME, NEW_AUTHOR_PATRONYMIC);

        dao.update(expectedAuthor);

        Optional<Author> actualAuthor = dao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(Optional.of(expectedAuthor));
    }

    @Test
    @DisplayName(" должен удалять author из БД по Id")
    void shouldDeleteAuthorById() {
        Optional<Author> expectedAuthor = dao.getById(EXISTING_AUTHOR_ID);
        assertThat(expectedAuthor).isNotEmpty();

        dao.deleteById(EXISTING_AUTHOR_ID);

        Optional<Author> actualAuthor = dao.getById(EXISTING_AUTHOR_ID);

        assertThat(actualAuthor).isEmpty();
    }

    @Test
    @DisplayName("должен возвращать author по полному имени")
    void shouldReturnExpectedAuthorByFullName() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_SURNAME, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_PATRONYMIC);

        Optional<Author> actualAuthor = dao.getByFullName(expectedAuthor);

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(Optional.of(expectedAuthor));
    }
}