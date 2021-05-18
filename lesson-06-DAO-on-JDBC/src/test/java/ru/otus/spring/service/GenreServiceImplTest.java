package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис по работе с genre")
class GenreServiceImplTest {
    private static final String NEW_GENRE_NAME = "Comedy";
    private static final long NEW_GENRE_ID = 2L;

    private GenreService genreService;
    @Mock
    private GenreDao genreDao;

    @BeforeEach
    void setUp() {
        genreService = new GenreServiceImpl(genreDao);
    }

    @Test
    @DisplayName("должен добавлять новую genre")
    void shouldInsertNewGenre() {
        Genre newGenre = new Genre(NEW_GENRE_NAME);

        Mockito.when(genreDao.insert(newGenre)).thenReturn(NEW_GENRE_ID);

        genreService.insert(newGenre);

        Mockito.verify(genreDao, Mockito.times(1)).insert(newGenre);

    }
}