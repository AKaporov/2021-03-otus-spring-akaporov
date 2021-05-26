package ru.otus.spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис по работе с author")
class AuthorServiceImplTest {
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Толстой А. Н.";

    private AuthorService authorService;
    @Mock
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorService = new AuthorServiceImpl(authorDao);
    }

    @Test
    @DisplayName("должен получать author по его id")
    void shouldGetAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);

        Mockito.when(authorDao.getById(EXISTING_AUTHOR_ID)).thenReturn(expectedAuthor);
        Author actualAuthor = authorService.getById(EXISTING_AUTHOR_ID);

        Assertions.assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}