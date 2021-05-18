package ru.otus.spring.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookToAuthorToGenreLink;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({BookToAuthorToGenreLinkDaoJdbc.class, BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@DisplayName("Dao для работы с BookToAuthorToGenreLink")
class BookToAuthorToGenreLinkDaoJdbcTest {

    private static final long EXISTING_ID = 1L;
    private static final long NEW_ID = 2L;
    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "Golden Key";
    private static final long NEW_BOOK_ID = 2L;
    private static final String NEW_BOOK_TITLE = "The Bremen Town Musicians";
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_SURNAME = "Tolstoy";
    private static final String EXISTING_AUTHOR_NAME = "Alexey";
    private static final String EXISTING_AUTHOR_PATRONYMIC = "Nikolaevich";
    private static final long NEW_AUTHOR_ID = 2L;
    private static final String NEW_AUTHOR_SURNAME = "Lagin";
    private static final String NEW_AUTHOR_NAME = "Lazar";
    private static final String NEW_AUTHOR_PATRONYMIC = "Iosifovich";
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Story";
    private static final long NEW_GENRE_ID = 2L;
    private static final String NEW_GENRE_NAME = "Comedy";

    @Autowired
    private BookToAuthorToGenreLinkDaoJdbc dao;
    @Autowired
    private BookDaoJdbc bookDao;
    @Autowired
    private AuthorDaoJdbc authorDao;
    @Autowired
    private GenreDaoJdbc genreDao;

    @Test
    @DisplayName("должен возвращать ожидаемую связь по переданному book_title")
    void shouldReturnExpectedByBookTitle() {
        Book existingBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);
        Author existingAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_SURNAME, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_PATRONYMIC);
        Genre existingGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        BookToAuthorToGenreLink expected = new BookToAuthorToGenreLink(EXISTING_ID, existingBook, existingAuthor, existingGenre);

        Optional<BookToAuthorToGenreLink> actual = dao.getByBookTitle(EXISTING_BOOK_TITLE);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(expected));
    }

    @Test
    @DisplayName("должен возвращать ожидаемую связь по переданному link_id")
    void shouldReturnExpectedByLinkId() {
        Book existingBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);
        Author existingAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_SURNAME, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_PATRONYMIC);
        Genre existingGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        BookToAuthorToGenreLink expected = new BookToAuthorToGenreLink(EXISTING_ID, existingBook, existingAuthor, existingGenre);

        Optional<BookToAuthorToGenreLink> actual = dao.getById(EXISTING_ID);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(expected));
    }

    @Test
    @DisplayName("должен добавлять новую связь book с author с genre")
    void shouldInsertNewLinkByBook() {
        BookToAuthorToGenreLink expectedLink = getExpectedLink();

        Book insertBook = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE);
        Author insertAuthor = new Author(NEW_AUTHOR_ID, NEW_AUTHOR_SURNAME, NEW_AUTHOR_NAME, NEW_AUTHOR_PATRONYMIC);
        Genre insertGenre = new Genre(NEW_GENRE_ID, NEW_GENRE_NAME);
        BookToAuthorToGenreLink insertLink = new BookToAuthorToGenreLink(insertBook, insertAuthor, insertGenre);

        long newLink = dao.insert(insertLink);

        Optional<BookToAuthorToGenreLink> actualLink = dao.getById(newLink);

        Assertions.assertThat(actualLink).usingRecursiveComparison().isEqualTo(Optional.of(expectedLink));
    }

    private BookToAuthorToGenreLink getExpectedLink() {
        Book expectedBook = new Book(NEW_BOOK_TITLE);
        long expectedBookId = bookDao.insert(expectedBook);
        expectedBook.setId(expectedBookId);

        Author expectedAuthor = new Author(NEW_AUTHOR_SURNAME, NEW_AUTHOR_NAME, NEW_AUTHOR_PATRONYMIC);
        long expectedAuthorId = authorDao.insert(expectedAuthor);
        expectedAuthor.setId(expectedAuthorId);

        Genre expectedGenre = new Genre(NEW_GENRE_NAME);
        long expectedGenreId = genreDao.insert(expectedGenre);
        expectedGenre.setId(expectedGenreId);

        return new BookToAuthorToGenreLink(NEW_ID, expectedBook, expectedAuthor, expectedGenre);
    }

    @Test
    @DisplayName("должен удалять связи по id")
    void shouldDeleteLinkById() {
        Optional<BookToAuthorToGenreLink> expectedLink = dao.getById(EXISTING_ID);
        assertThat(expectedLink).isNotEmpty();

        dao.deleteById(EXISTING_ID);

        Optional<BookToAuthorToGenreLink> actualLink = dao.getById(EXISTING_ID);
        assertThat(actualLink).isEmpty();
    }
}