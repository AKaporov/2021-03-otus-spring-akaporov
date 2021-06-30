package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.data=author-test.sql"})
@Import(AuthorRepositoryJpaImpl.class)
@DisplayName("Репозиторий на основе Jpa для работы с Авторами")
class AuthorRepositoryJpaImplTest {
    private static final long AUTHOR_EXISTS_ID = 1;
    private static final String AUTHOR_NEW_NAME = "Pushkin A. S.";
    private static final int AUTHOR_EXISTS_COUNT = 2;
    private static final long AUTHOR_EMPTY_ID = 0;
    private static final long AUTHOR_NOT_EXISTS_ID = 1000;
    private static final String AUTHOR_NOT_EXISTS_NAME = "Street author";
    @Autowired
    private AuthorRepositoryJpaImpl repositoryJpa;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("дожен сохранять нового автора")
    void shouldSaveNewAuthor() {
        Author expectedAuthor = new Author(AUTHOR_EMPTY_ID, AUTHOR_NEW_NAME);

        repositoryJpa.save(expectedAuthor);

        assertThat(expectedAuthor.getId()).isGreaterThan(AUTHOR_EMPTY_ID);

        Author actualAuthor = em.find(Author.class, expectedAuthor.getId());
        assertThat(actualAuthor).isNotNull().matches(a -> AUTHOR_NEW_NAME.equalsIgnoreCase(a.getName()));
    }

    @Test
    @DisplayName("должен находить автора по его id")
    void shouldFindAuthorById() {
        Author expectedAuthor = em.find(Author.class, AUTHOR_EXISTS_ID);

        Optional<Author> actualAuthor = repositoryJpa.findById(expectedAuthor.getId());

        assertThat(actualAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("должен находить автора по его имени")
    void shouldFindAuthorByName() {
        Author expectedAuthor = em.find(Author.class, AUTHOR_EXISTS_ID);

        Optional<Author> actualAuthor = repositoryJpa.findByName(expectedAuthor.getName());

        assertThat(actualAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("должен находить все книги")
    void shouldFindAllAuthor() {
        List<Author> actualAuthors = repositoryJpa.findAll();

        assertThat(actualAuthors).isNotNull().isNotEmpty().hasSize(AUTHOR_EXISTS_COUNT)
                .allMatch(a -> !"".equalsIgnoreCase(a.getName()))
                .allMatch(a -> a.getId() > 0);
    }

    @Test
    @DisplayName("должен изменять заданного автора по его id")
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(AUTHOR_EXISTS_ID, AUTHOR_NEW_NAME);

        Author foundAuthor = em.find(Author.class, expectedAuthor.getId());
        String oldName = foundAuthor.getName();
        em.detach(foundAuthor);

        repositoryJpa.save(expectedAuthor);

        Author actualAuthor = em.find(Author.class, expectedAuthor.getId());

        assertThat(actualAuthor.getName()).isNotNull().isNotEmpty().isNotEqualTo(oldName).isEqualTo(expectedAuthor.getName());
    }

    @Test
    @DisplayName("должен удалять конкретного автора")
    void shouldDeleteAuthorById() {
        Author foundAuthor = em.find(Author.class, AUTHOR_EXISTS_ID);
        assertThat(foundAuthor).isNotNull();

        repositoryJpa.remove(foundAuthor);

        Author actualAuthor = em.find(Author.class, AUTHOR_EXISTS_ID);

        assertThat(actualAuthor).isNull();
    }

    @Test
    @DisplayName("должен удалять конкретного автора")
    void shouldThrowRepositoryExceptionWhenDeleting() {
        Author notExistsAuthor = new Author(AUTHOR_NOT_EXISTS_ID, AUTHOR_NOT_EXISTS_NAME);

        assertThatThrownBy(() -> repositoryJpa.remove(notExistsAuthor)).isInstanceOf(RepositoryException.class);
    }
}