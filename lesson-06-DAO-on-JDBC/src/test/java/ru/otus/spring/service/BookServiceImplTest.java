package ru.otus.spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис по работе с book")
class BookServiceImplTest {
    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "Golden Key";

    private BookService bookService;
    @Mock
    private BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookDao);
    }

    @Test
    @DisplayName("должен получать book по его id")
    void shouldGetBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE);

        Mockito.when(bookDao.getById(EXISTING_BOOK_ID)).thenReturn(Optional.of(expectedBook));

        Book actualBook = bookService.getById(EXISTING_BOOK_ID);

        Assertions.assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}