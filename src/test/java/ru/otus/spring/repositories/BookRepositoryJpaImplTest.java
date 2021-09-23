package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.domain.Book;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий на основе Jpa для работы с Книгами")
class BookRepositoryJpaImplTest {
    private static final long BOOK_EXISTS_ONE_ID = 1;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Test
    @DisplayName("дожен возвращать конкретную книгу по его названию")
    void shouldFindBookByTitle() {
        Book expectedBook = em.find(Book.class, BOOK_EXISTS_ONE_ID);

        Optional<Book> actualBook = repositoryJpa.findByTitle(expectedBook.getTitle());

        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(Optional.of(expectedBook));
    }
}