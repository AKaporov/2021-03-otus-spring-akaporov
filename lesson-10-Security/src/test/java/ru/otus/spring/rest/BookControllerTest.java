package ru.otus.spring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.generate.BookGenerator;
import ru.otus.spring.service.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Rest контроллер по работе с книгами")
public class BookControllerTest {
    private static final String GOLDEN_KEY_REVIEW_NEW = "Beautiful pictures";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @WithMockUser(authorities = {"ROLE_ADMIN", "ROLE_READER"})
    @Test
    @DisplayName("должен возвращать конкретную книгу по её названию. Название передано через RequestParam")
    void shouldReturnCorrectBookByTitleInRequestParam() throws Exception {
        BookDto expectedResult = BookGenerator.getBookDtoGoldenKey();

        mvc.perform(
                get("/api/v1/books")
                        .param("BookTitle", expectedResult.getTitle())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN", "ROLE_READER"})
    @Test
    @DisplayName("должен возвращать корректный список всех книг библиотеки")
    void shouldReturnCorrectBookList() throws Exception {
        List<BookDto> expectedResult = new ArrayList<>(4);
        expectedResult.add(BookGenerator.getBookDtoGoldenKey());
        expectedResult.add(BookGenerator.getBookDtoDreamers());
        expectedResult.add(BookGenerator.getBookDtoTimurAndHisTeam());
        expectedResult.add(BookGenerator.getBookDtoSchool());

        mvc.perform(get("/api/v1/books/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN", "ROLE_READER"})
    @Test
    @DisplayName("должен возврвщать отзывы о книге по её названию")
    void shouldReturnCorrectBookReviewByBookTitleInPath() throws Exception {
        BookDto goldenKeyBookDto = BookGenerator.getBookDtoGoldenKey();
        BookReviewDto expectedResult = BookGenerator.getReviewsByGoldenKey();

        mvc.perform(get("/api/v1/books/{BookTitle}/reviews", goldenKeyBookDto.getTitle()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @Test
    @Transactional
    @DisplayName("должен корректно сохранять новую книгу переданную через тело запроса")
    void shouldCorrectSaveNewBookInRequestBody() throws Exception {
        BookDto booksDtoToSave = BookGenerator.getNewBookDtoToSave();
        String expectedResult = mapper.writeValueAsString(booksDtoToSave);

        mvc.perform(
                post("/api/v1/books").contentType(APPLICATION_JSON).content(expectedResult)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @Test
    @Transactional
    @DisplayName("должен корретно удалять книгу")
    void shouldCorrectDeleteBookByBookTitleInPath() throws Exception {
        BookDto bookDtoToDelete = BookGenerator.getBookDtoDreamers();

        mvc.perform(
                get("/api/v1/books").param("BookTitle", bookDtoToDelete.getTitle())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookDtoToDelete)));

        mvc.perform(
                delete("/api/v1/books/{BookTitle}", bookDtoToDelete.getTitle())
        ).andExpect(status().isOk());


        mvc.perform(
                get("/api/v1/books").param("BookTitle", bookDtoToDelete.getTitle())
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookNotFoundException));

    }

    @WithMockUser(authorities = {"ROLE_ADMIN", "ROLE_READER"})
    @Test
    @Transactional
    @DisplayName("должен коррктно сохранять отзыв на книгу")
    void shouldCorrectSaveNewReviewInPathAndRequestParam() throws Exception {
        BookDto goldenKeyBookDto = BookGenerator.getBookDtoGoldenKey();
        BookReviewDto expectedBookReview = BookGenerator.getReviewsByGoldenKey();
        expectedBookReview.getReviews().add(GOLDEN_KEY_REVIEW_NEW);

        mvc.perform(
                post("/api/v1/books/{BookTitle}/review", goldenKeyBookDto.getTitle()).param("review", GOLDEN_KEY_REVIEW_NEW)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(
                get("/api/v1/books/{BookTitle}/reviews", goldenKeyBookDto.getTitle())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBookReview)));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN", "ROLE_READER"})
    @Test
    @DisplayName("должен возвращать ошибку, если книга по наименованию не найдена")
    void shouldReturnExpectedErrorWhenBookNotFound() throws Exception {
        Book notExistsBook = BookGenerator.getNotExistsBook();

        mvc.perform(
                get("/api/v1/books").param("BookTitle", notExistsBook.getTitle())
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookNotFoundException));
    }
}
