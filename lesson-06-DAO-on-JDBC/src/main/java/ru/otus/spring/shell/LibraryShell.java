package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.LibraryService;
import ru.otus.spring.service.LocaleService;

import java.util.Collections;
import java.util.List;

@ShellComponent
@Service
@RequiredArgsConstructor
public class LibraryShell {
    private final LocaleService localeService;
    private final LibraryService libraryService;

    @ShellMethod(key = {"iba", "insertBookAll"}, value = "Add a book with all related information, please indicate its \"Title\" \"Author's name\" \"Genre\"")
    public String insertBookWithAllInfo(@ShellOption String titleBook,
                                        @ShellOption String authorName,
                                        @ShellOption String genreName) {
        libraryService.insertBookWithAllInfo(titleBook, authorName, genreName);
        return localeService.getMessage("shell.book.all.insert", new String[]{titleBook});
    }

    @ShellMethod(key = {"fb", "findBook"}, value = "Get all information about a book by its title")
    public String getAllInfoByBookTitle(@ShellOption String titleBook) {
        Book foundBook = libraryService.gelBookAllInfoByBookTitle(titleBook);

        return localeService.getMessage("shell.book.all.information",
                new String[]{foundBook.getTitle(),
                        foundBook.getAuthor().getName(),
                        foundBook.getGenre().getName()});
    }

    @ShellMethod(key = {"db", "deleteBook"}, value = "Delete a book with all related information, by its title")
    public String deleteBookWithAllInfoByTitle(@ShellOption String titleBook) {
        libraryService.deleteBookWithAllInfoByBookTitle(titleBook);
        return localeService.getMessage("shell.book.all.delete", new String[]{titleBook});
    }

    @ShellMethod(key = {"fab", "findAllBook"}, value = "Find all books and related information about them")
    public List<String> getAllBookInfo() {
        List<Book> list = libraryService.getAllBookInfo();
        String message = localeService.getMessage("shell.book.all", new List[]{list});

        return Collections.singletonList(message);
    }
}
