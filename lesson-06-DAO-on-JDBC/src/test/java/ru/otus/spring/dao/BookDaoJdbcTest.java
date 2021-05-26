package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.exception.DaoException;
import ru.otus.spring.domain.Book;
import ru.otus.spring.generate.BookGenerator;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("Dao для работы с Book")
class BookDaoJdbcTest {

    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "The Golden Key, or the Adventures of Pinocchio";
    private static final long NEW_BOOK_ID = 2L;
    private static final String NEW_BOOK_TITLE = "The Bremen Town Musicians";

    @Autowired
    private BookDaoJdbc dao;

    @Test
    @DisplayName(" должен возвращать ожадаемую book по его id")
    void shouldReturnExpectedBookById() {
        Book expectedBook = BookGenerator.createNewBook(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);

        Book actualBook = dao.getAllById(EXISTING_BOOK_ID);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName(" должен добавлять новую book в БД")
    void shouldInsertNewBook() {
        Book expectedBook = BookGenerator.createNewBook(NEW_BOOK_ID, NEW_BOOK_TITLE);

        Book insertBook = dao.insertAll(BookGenerator.createNewBook(NEW_BOOK_TITLE));

        Book actualBook = dao.getAllById(insertBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName(" должен возвращать ожидаемый список books")
    void shouldReturnAllBooks() {
        Book expectedBook = BookGenerator.createNewBook(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);

        List<Book> actualBooksList = dao.getAll();

        assertThat(actualBooksList).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(expectedBook);
    }

    @Test
    @DisplayName(" должен изменять название books по его id")
    void shouldUpdateTitleBookById() {
        Book expectedBook = BookGenerator.createNewBook(EXISTING_BOOK_ID, NEW_BOOK_TITLE);

        dao.update(expectedBook);

        Book actualBook = dao.getAllById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName(" должен удалять book по его id")
    void shouldDeleteBookById() {
        assertThatCode(() -> dao.getAllById(EXISTING_BOOK_ID)).doesNotThrowAnyException();

        dao.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> dao.getAllById(EXISTING_BOOK_ID)).isInstanceOf(DaoException.class);
    }

    @Test
    @DisplayName(" должен возвращать ожидаемую book по его title")
    void shouldReturnExpectedBookByTitle() {
        Book expectedBook = BookGenerator.createNewBook(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);

        Optional<Book> actualBook = dao.getAllByTitle(EXISTING_BOOK_TITLE);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(Optional.of(expectedBook));
    }

    @Test
    @DisplayName(" должен кидать DaoException, если не нашел book по id")
    void shouldReturnDaoExceptionByNotExistsBookId() {
        assertThatThrownBy(() -> dao.getAllById(NEW_BOOK_ID)).isInstanceOf(DaoException.class);
    }
}