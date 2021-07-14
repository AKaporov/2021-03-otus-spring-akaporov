package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.generate.BookGenerator;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@DisplayName("Rest контроллер по работе с книгами")
class BookControllerTest {
    private static final String GOLDEN_KEY_REVIEW_NEW = "Beautiful pictures";
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("должен возвращать конкретную книгу по её названию")
    void shouldReturnCorrectBookByTitleInRequestParams() throws Exception {
        BookDto expectedResult = BookGenerator.getBookDtoGoldenKey();

        when(bookService.findBookByTitle(expectedResult.getTitle())).thenReturn(expectedResult);

        mvc.perform(get("/api/v1/books").param("BookTitle", expectedResult.getTitle()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возвращать корректный список всех книг библиотеки")
    void shouldReturnCorrectBookList() throws Exception {
        List<BookDto> expectedResult = new ArrayList<>(2);
        expectedResult.add(BookGenerator.getBookDtoGoldenKey());
        expectedResult.add(BookGenerator.getBookDtoDreamers());

        when(bookService.findAllBooks()).thenReturn(expectedResult);

        mvc.perform(get("/api/v1/books/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возврвщать отзывы о книге по её названию")
    void shouldReturnCorrectBookReviewByBookTitleInPath() throws Exception {
        BookDto goldenKeyBookDto = BookGenerator.getBookDtoGoldenKey();
        BookReviewDto expectedResult = BookGenerator.getReviewsByGoldenKey();

        when(bookService.findBookReviewsByTitle(goldenKeyBookDto.getTitle())).thenReturn(expectedResult);

        mvc.perform(get("/api/v1/books/{BookTitle}/reviews", goldenKeyBookDto.getTitle()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен корректно сохранять новую книгу переданную через тело запроса")
    void shouldCorrectSaveNewBookInRequestBody() throws Exception {
        BookDto booksDtoToSave = BookGenerator.getNewBookDtoToSave();
        when(bookService.save(any())).thenReturn(booksDtoToSave);
        String expectedResult = mapper.writeValueAsString(booksDtoToSave);

        mvc.perform(
                post("/api/v1/books").contentType(APPLICATION_JSON).content(expectedResult)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    @DisplayName("должен корретно удалять книгу")
    void shouldCorrectDeleteBookByBookTitleInPath() throws Exception {
        BookDto bookDtoToDelete = BookGenerator.getBookDtoDreamers();
        when(bookService.findBookByTitle(bookDtoToDelete.getTitle())).thenReturn(bookDtoToDelete);

        mvc.perform(
                delete("/api/v1/books/{BookTitle}", bookDtoToDelete.getTitle())
        ).andExpect(status().isOk());

        verify(bookService, times(1)).remove(bookDtoToDelete);
    }

    @Test
    @DisplayName("должен коррктно сохранять отзыв на книгу")
    void shouldCorrectSaveNewReviewInPathAndRequestParam() throws Exception {
        BookDto goldenKeyBookDto = BookGenerator.getBookDtoGoldenKey();

        mvc.perform(
                post("/api/v1/books/{BookTitle}/review", goldenKeyBookDto.getTitle()).param("review", GOLDEN_KEY_REVIEW_NEW)
        ).andExpect(status().isOk());

        verify(bookService, times(1)).saveReview(goldenKeyBookDto.getTitle(), GOLDEN_KEY_REVIEW_NEW);
    }

    @Test
    @DisplayName("должен возвращать ошибку, если книга на наименованию не найдена")
    void shouldReturnExpectedErrorWhenBookNotFound() throws Exception {
        Book notExistsBook = BookGenerator.getNotExistsBook();

        when(bookService.findBookByTitle(notExistsBook.getTitle())).thenThrow(BookNotFoundException.class);

        mvc.perform(
                get("/api/v1/books").param("BookTitle", notExistsBook.getTitle())
        )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookNotFoundException));
    }
}