package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.AccountDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.generate.BookGenerator;
import ru.otus.spring.service.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Rest контроллер по работе с книгами")
public class BookControllerTest {
    public static final String READER_CREDENTIALS = "$2a$16$FltGrygFMPyOoZG2P15lceoIRFmr1Rwq3MDtxouGY25sPtQnxDbzy";
    public static final String ADMIN_CREDENTIALS = "$2a$16$FMSODhQnsyHfuMEdKiMUu.46Qqmzr084FqHA8L0Fp8MIYmIiWBZUa";
    private static final String GOLDEN_KEY_REVIEW_NEW = "Beautiful pictures";
    public static final String INVALID_SIGNATURE_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private String adminToken = "";
    private String readerToken = "";

    @BeforeAll
    void setUp() {
        adminToken = getToken(new AccountDto("admin", "password"), ADMIN_CREDENTIALS);
        readerToken = getToken(new AccountDto("reader", "123456"), READER_CREDENTIALS);
    }

    @SneakyThrows
    private String getToken(AccountDto accountDto, String credentials) {
        Map<String, String> tokens = new HashMap<>();
        String requestBody = mapper.writeValueAsString(accountDto);

        if (!tokens.containsKey(credentials)) {
            MvcResult result = mvc.perform(
                    post("/api/v1/token")
                            .contentType(APPLICATION_JSON)
                            .content(requestBody)
            )
                    .andDo(print())  // выводит полученный результат в консоль, поможет, если тест упал
                    .andExpect(status().isOk())
                    .andReturn();
            tokens.put(credentials, result.getResponse().getContentAsString());
        }
        return tokens.get(credentials);
    }

    @Test
    @DisplayName("должен вернуть 3хх ошибку, если токен не передан")
    void shouldReturn4xxErrorHandleNoToken() throws Exception {
        mvc.perform(
                get("/api/v1/books")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("должен возвращать конкретную книгу по её названию для пользователя с ролью ADMIN. Название передано через RequestParam")
    void shouldReturnCorrectBookByTitleRoleAdminInRequestParam() throws Exception {
        BookDto expectedResult = BookGenerator.getBookDtoGoldenKey();

        mvc.perform(
                get("/api/v1/books")
                        .param("BookTitle", expectedResult.getTitle())
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возвращать конкретную книгу по её названию для пользователя с ролью READER. Название передано через RequestParam")
    void shouldReturnCorrectBookByTitleRoleReaderInRequestParam() throws Exception {
        BookDto expectedResult = BookGenerator.getBookDtoGoldenKey();

        mvc.perform(
                get("/api/v1/books")
                        .param("BookTitle", expectedResult.getTitle())
                        .header("Authorization", "Bearer " + readerToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возвращать корректный список всех книг библиотеки")
    void shouldReturnCorrectBookList() throws Exception {
        List<BookDto> expectedResult = new ArrayList<>(4);
        expectedResult.add(BookGenerator.getBookDtoGoldenKey());
        expectedResult.add(BookGenerator.getBookDtoDreamers());
        expectedResult.add(BookGenerator.getBookDtoTimurAndHisTeam());
        expectedResult.add(BookGenerator.getBookDtoSchool());

        mvc.perform(get("/api/v1/books/")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возврвщать отзывы о книге по её названию")
    void shouldReturnCorrectBookReviewByBookTitleInPath() throws Exception {
        BookDto goldenKeyBookDto = BookGenerator.getBookDtoGoldenKey();
        BookReviewDto expectedResult = BookGenerator.getReviewsByGoldenKey();

        mvc.perform(
                get("/api/v1/books/{BookTitle}/reviews", goldenKeyBookDto.getTitle())
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @Transactional
    @DisplayName("должен корректно сохранять новую книгу переданную через тело запроса для пользователя с ролью ADMIN")
    void shouldCorrectSaveNewBookInRequestBodyRoleAdmin() throws Exception {
        BookDto booksDtoToSave = BookGenerator.getNewBookDtoToSave();
        String requestBody = mapper.writeValueAsString(booksDtoToSave);

        mvc.perform(
                post("/api/v1/books").contentType(APPLICATION_JSON).content(requestBody)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(requestBody));
    }

    @Test
    @Transactional
    @DisplayName("должен вернуть 4xx ошибку, если у пользователя нет прав для сохранения новой книги")
    void shouldReturn4xxErrorRoleReaderTrySaveNewBookInRequestBody() throws Exception {
        BookDto booksDtoToSave = BookGenerator.getNewBookDtoToSave();
        String requestBody = mapper.writeValueAsString(booksDtoToSave);

        mvc.perform(
                post("/api/v1/books").contentType(APPLICATION_JSON).content(requestBody)
                        .header("Authorization", "Bearer " + readerToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    @DisplayName("пользователь с ролью ADMIN должен корретно удалять книгу")
    void shouldRoleAdminCorrectDeleteBookByBookTitleInPath() throws Exception {
        BookDto bookDtoToDelete = BookGenerator.getBookDtoDreamers();

        mvc.perform(
                get("/api/v1/books").param("BookTitle", bookDtoToDelete.getTitle())
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookDtoToDelete)));

        mvc.perform(
                delete("/api/v1/books/{BookTitle}", bookDtoToDelete.getTitle())
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());


        mvc.perform(
                get("/api/v1/books").param("BookTitle", bookDtoToDelete.getTitle())
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof BookNotFoundException));
    }

    @Test
    @DisplayName("должен вернуть 403 ошибку, если пользователь у которого нет прав попробует удалить книгу")
    void shouldReturn403ErrorRoleReaderTryDeleteBookByBookTitleInPath() throws Exception {
        BookDto bookDtoToDelete = BookGenerator.getBookDtoDreamers();

        mvc.perform(
                delete("/api/v1/books/{BookTitle}", bookDtoToDelete.getTitle())
                        .header("Authorization", "Bearer " + readerToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(403));
    }

    @Test
    @Transactional
    @DisplayName("должен коррктно сохранять отзыв на книгу")
    void shouldCorrectSaveNewReviewInPathAndRequestParam() throws Exception {
        BookDto goldenKeyBookDto = BookGenerator.getBookDtoGoldenKey();
        BookReviewDto expectedBookReview = BookGenerator.getReviewsByGoldenKey();
        expectedBookReview.getReviews().add(GOLDEN_KEY_REVIEW_NEW);

        mvc.perform(
                post("/api/v1/books/{BookTitle}/review", goldenKeyBookDto.getTitle())
                        .param("review", GOLDEN_KEY_REVIEW_NEW)
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mvc.perform(
                get("/api/v1/books/{BookTitle}/reviews", goldenKeyBookDto.getTitle())
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedBookReview)));
    }

    @Test
    @DisplayName("должен возвращать ошибку, если книга по наименованию не найдена")
    void shouldReturnExpectedErrorWhenBookNotFound() throws Exception {
        Book notExistsBook = BookGenerator.getNotExistsBook();

        mvc.perform(
                get("/api/v1/books").param("BookTitle", notExistsBook.getTitle())
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BookNotFoundException));
    }
}
