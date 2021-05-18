package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.BookToAuthorToGenreLink;

import java.util.Collections;
import java.util.List;

@ShellComponent
@Service
@RequiredArgsConstructor
public class LibraryShell {
    private final LocaleService localeService;
    private final LibraryService libraryService;

    @ShellMethod(key = {"ibl", "insertBookLink"}, value = "Add a book with all related information, please indicate its \"Title\" \"SurNameAuthor NameAuthor PatronymicAuthor\" \"Genre\"")
    public String insertLink(@ShellOption String titleBook,
                             @ShellOption String fullNameAuthor,
                             @ShellOption String genre) {
        libraryService.insertLinkWithAllInfo(titleBook, fullNameAuthor, genre);
        return localeService.getMessage("shell.link.insert", new String[]{titleBook});
    }

    @ShellMethod(key = {"fbl", "findBookLink"}, value = "Get all information about a book by its title")
    public String getLinkByBookTitle(@ShellOption String titleBook) {
        BookToAuthorToGenreLink LinkFoundBook = libraryService.getLinkByBookTitle(titleBook);

        return localeService.getMessage("shell.link.information",
                new String[]{LinkFoundBook.getBook().getTitle(),
                        LinkFoundBook.getAuthor().getSurName(),
                        LinkFoundBook.getAuthor().getName(),
                        LinkFoundBook.getAuthor().getPatronymic(),
                        LinkFoundBook.getGenre().getName()});
    }

    @ShellMethod(key = {"dbl", "deleteBookLink"}, value = "Delete a book with all related information, by its title")
    public String deleteLinkByTitle(@ShellOption String titleBook) {
        libraryService.deleteLinkByBookTitle(titleBook);
        return localeService.getMessage("shell.link.delete", new String[]{titleBook});
    }

    @ShellMethod(key = {"fabl", "findAllBookLink"}, value = "Find all books and related information about them")
    public List<String> getAllLinkBook() {
        List<BookToAuthorToGenreLink> list = libraryService.getAllLink();
        String message = localeService.getMessage("shell.link.all", new List[]{list});

        return Collections.singletonList(message);
    }
}
