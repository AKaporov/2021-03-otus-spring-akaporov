package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.service.LocaleService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@DisplayName("Тест команд shell")
class LibraryShellTest {
    private static final String EXISTING_BOOK_TITLE = "Dreamers";
    private static final String EXISTING_AUTHOR_NAME = "Nosov N.";
    private static final String EXISTING_GENRE_NAME = "Library of the Soviet novel";

    private static final String COMMAND_FIND_BOOK_PATTERN = "%s %s";
    private static final String COMMAND_FIND_BOOK = "findBook";
    private static final String COMMAND_FIND_BOOK_SHORT = "fb";

    @Autowired
    private LocaleService localeService;
    @Autowired
    private Shell shell;

    @Test
    @DisplayName("должен возвращать информацию о книге (author, genre)")
    void shouldGetAllBookInfo() {
        BookDto expectedBook = getExpectedBook();

        String expectedMessage = localeService.getMessage("shell.book.all.information",
                new String[]{expectedBook.getTitle(), expectedBook.getAuthor(), expectedBook.getGenre()});

        String actualBook = (String) shell.evaluate(() -> String.format(COMMAND_FIND_BOOK_PATTERN, COMMAND_FIND_BOOK, EXISTING_BOOK_TITLE));
        String actualBookShort = (String) shell.evaluate(() -> String.format(COMMAND_FIND_BOOK_PATTERN, COMMAND_FIND_BOOK_SHORT, EXISTING_BOOK_TITLE));

        assertAll(() -> {
            assertThat(actualBook).isNotNull().isEqualTo(expectedMessage);
            assertThat(actualBookShort).isNotNull().isEqualTo(expectedMessage);
        });
    }

    private BookDto getExpectedBook() {
        return new BookDto(EXISTING_BOOK_TITLE, EXISTING_AUTHOR_NAME, EXISTING_GENRE_NAME);
    }
}