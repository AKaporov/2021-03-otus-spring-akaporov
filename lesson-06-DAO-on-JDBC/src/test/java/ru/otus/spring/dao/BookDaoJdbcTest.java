package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("Dao для работы с Book")
class BookDaoJdbcTest {

    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "Golden Key";
    private static final long NEW_BOOK_ID = 2L;
    private static final String NEW_BOOK_TITLE = "The Bremen Town Musicians";

    @Autowired
    private BookDaoJdbc dao;

    @Test
    @DisplayName(" должно возвращать ожадаемую book по его id")
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);

        Optional<Book> actualBook = dao.getById(EXISTING_BOOK_ID);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(Optional.of(expectedBook));
    }

    @Test
    @DisplayName(" должно добавлять новую book в БД")
    void shouldInsertNewBook() {
        Book expectedBook = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE);

        Book insertBook = new Book(NEW_BOOK_TITLE);
        long idNewBook = dao.insert(insertBook);

        Optional<Book> actualBook = dao.getById(idNewBook);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(Optional.of(expectedBook));
    }

    @Test
    @DisplayName(" должен возвращать ожидаемый список books")
    void shouldReturnAllBooks() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);

        List<Book> actualBooksList = dao.getAll();

        assertThat(actualBooksList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedBook);
    }

    @Test
    @DisplayName(" должен изменять название books по его id")
    void shouldUpdateTitleBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, NEW_BOOK_TITLE);

        dao.update(expectedBook);

        Optional<Book> actualBook = dao.getById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(Optional.of(expectedBook));
    }

    @Test
    @DisplayName(" должен удалять book по его id")
    void shouldDeleteBookById() {
        Optional<Book> expectedBook = dao.getById(EXISTING_BOOK_ID);
        assertThat(expectedBook).isNotEmpty();

        dao.deleteById(EXISTING_BOOK_ID);

        Optional<Book> actualBook = dao.getById(EXISTING_BOOK_ID);
        assertThat(actualBook).isEmpty();
    }

    @Test
    @DisplayName(" должен возвращать ожидаемую book по его title")
    void shouldReturnExpectedBookByTitle() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);

        Optional<Book> actualBook = dao.getByTitle(EXISTING_BOOK_TITLE);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(Optional.of(expectedBook));
    }
}