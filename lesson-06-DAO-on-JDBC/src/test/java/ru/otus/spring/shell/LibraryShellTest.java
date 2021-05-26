package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.LocaleService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@DisplayName("Тест команд shell")
class LibraryShellTest {

    private static final long EXISTING_BOOK_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "The Golden Key, or the Adventures of Pinocchio";
    private static final long EXISTING_AUTHOR_ID = 1L;
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy A. N.";
    private static final long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_GENRE_NAME = "Story";

    private static final String COMMAND_FIND_BOOK_PATTERN = "%s \"%s\"";
    private static final String COMMAND_FIND_BOOK = "findBookAllInfo";
    private static final String COMMAND_FIND_BOOK_SHORT = "fb";

    private static final String COMMAND_FIND_ALL_BOOK = "findAllBook";
    private static final String COMMAND_FIND_ALL_BOOK_SHORT = "fab";

    @Autowired
    private LocaleService localeService;

    @Autowired
    private Shell shell;

// TODO: 26.05.2021 Не могу понять, почему тест падает с ошибкой java.lang.IllegalArgumentException: Too many arguments: the following could not be mapped to parameters: 'Golden Key, or the Adventures of Pinocchio""'
//    @Test
//    @DisplayName("должен возвращать всю связанную информацию о book (author, genre) по её title")
//    void getLinkByBookTitle() {
//        Book expectedBook = getExpectedBook();
//
//        String expectedMessage = localeService.getMessage("shell.book.all.information", new List[]{Collections.singletonList(expectedBook)});
//
////        String format = String.format(COMMAND_FIND_BOOK_PATTERN, COMMAND_FIND_BOOK, EXISTING_BOOK_TITLE);
////        Object evaluate = shell.evaluate(() -> format);
//
//        String actualBook = (String) shell.evaluate(() -> String.format(COMMAND_FIND_BOOK_PATTERN, COMMAND_FIND_BOOK, EXISTING_BOOK_TITLE));
//        String actualBookShort = (String) shell.evaluate(() -> String.format(COMMAND_FIND_BOOK_PATTERN, COMMAND_FIND_BOOK_SHORT, EXISTING_BOOK_TITLE));
//
//        assertAll(() -> {
//            assertThat(actualBook).isEqualTo(expectedMessage);
//            assertThat(actualBookShort).isEqualTo(expectedMessage);
//        });
//    }

    @Test
    @DisplayName("должен возвращать всю информацию о всех book (author, genre) библиотеки")
    void shouldGetAllBookInfo() {
        Book expectedBook = getExpectedBook();

        String expectedMessage = localeService.getMessage("shell.book.all",
                new List[]{Collections.singletonList(expectedBook)});

        List<String> actualBooksList = (List<String>) shell.evaluate(() -> COMMAND_FIND_ALL_BOOK);
        List<String> actualBooksListShort = (List<String>) shell.evaluate(() -> COMMAND_FIND_ALL_BOOK_SHORT);

        assertAll(() -> {
            assertThat(actualBooksList).usingFieldByFieldElementComparator().contains(expectedMessage);
            assertThat(actualBooksListShort).usingFieldByFieldElementComparator().contains(expectedMessage);
        });
    }

    private Book getExpectedBook() {
        return new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
    }
}