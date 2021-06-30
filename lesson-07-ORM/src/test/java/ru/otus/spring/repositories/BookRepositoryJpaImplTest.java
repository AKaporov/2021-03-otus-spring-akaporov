package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.generate.BookGenerator;
import ru.otus.spring.repositories.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
@DisplayName("Репозиторий на основе Jpa для работы с Книгами")
class BookRepositoryJpaImplTest {
    private static final long BOOK_EXISTS_ONE_ID = 1;
    private static final int BOOK_EXISTS_COUNT = 4;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private BookRepositoryJpaImpl repositoryJpa;

    @Test
    @DisplayName("должен сохранять книгу")
    void shouldSaveNewBook() {
        Book bookToSave = BookGenerator.getNewBookToSave();

        repositoryJpa.save(bookToSave);

        assertThat(bookToSave.getId()).isPositive();

        Book actualBook = em.find(Book.class, bookToSave.getId());
        assertThat(actualBook).isNotNull().matches(b -> !"".equalsIgnoreCase(b.getTitle()))
                .matches(book -> book.getAuthor() != null && !"".equalsIgnoreCase(book.getAuthor().getName()))
                .matches(book -> book.getGenre() != null && !"".equalsIgnoreCase(book.getGenre().getName()))
                .matches(book -> book.getReviews() != null && book.getReviews().size() > 0 && book.getReviews().get(0).getId() > 0);

    }

    @Test
    @DisplayName("должен удалять книгу и отзывы")
    void shouldRemoveBook() {
        Book foundBook = em.find(Book.class, BOOK_EXISTS_ONE_ID);
        assertThat(foundBook).isNotNull();

        repositoryJpa.remove(foundBook);

        Book actualBook = em.find(Book.class, BOOK_EXISTS_ONE_ID);
        assertThat(actualBook).isNull();
    }

    @Test
    @DisplayName("должен удалять конкретного автора")
    void shouldThrowRepositoryExceptionWhenDeleting() {
        Book notExistsBook = BookGenerator.getNotExistsBook();

        assertThatThrownBy(() -> repositoryJpa.remove(notExistsBook)).isInstanceOf(RepositoryException.class);
    }

    @Test
    @DisplayName("дожен возвращать конкретную книгу по его названию")
    void shouldFindBookByTitle() {
        Book expectedBook = em.find(Book.class, BOOK_EXISTS_ONE_ID);

        Optional<Book> actualBook = repositoryJpa.findByTitle(expectedBook.getTitle());

        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(Optional.of(expectedBook));
    }

    @Test
    @DisplayName("должен возвращать все книги в библиотеке")
    void shouldFindAllBook() {
        List<Book> actualBooks = repositoryJpa.findAll();

        assertThat(actualBooks).isNotNull().isNotEmpty().hasSize(BOOK_EXISTS_COUNT);
    }
}