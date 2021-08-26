package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.data=author-test.sql"})
@DisplayName("Репозиторий на основе Jpa для работы с Авторами")
class AuthorRepositoryJpaImplTest {
    private static final long AUTHOR_EXISTS_ID = 1;
    @Autowired
    private AuthorRepositoryJpa repositoryJpa;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("должен находить автора по его имени")
    void shouldFindAuthorByName() {
        Author expectedAuthor = em.find(Author.class, AUTHOR_EXISTS_ID);

        Optional<Author> actualAuthor = repositoryJpa.findByName(expectedAuthor.getName());

        assertThat(actualAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}