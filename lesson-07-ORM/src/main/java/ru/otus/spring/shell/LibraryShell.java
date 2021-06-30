package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.service.LibraryService;
import ru.otus.spring.service.LocaleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@Service
@RequiredArgsConstructor
public class LibraryShell {
    private final LocaleService localeService;
    private final LibraryService libraryService;

    @ShellMethod(key = {"ib", "insertBook"}, value = "Add a book, please indicate its \"Title\" \"Author's name\" \"Genre\".")
    public String insertBook(@ShellOption String titleBook,
                             @ShellOption String authorName,
                             @ShellOption String genreName) {
        libraryService.insertBook(titleBook, authorName, genreName);
        return localeService.getMessage("shell.book.all.insert", new String[]{titleBook});
    }

    @ShellMethod(key = {"fb", "findBook"}, value = "Get information about a book by its \"Title\".")
    public String findBookByBookTitle(@ShellOption String titleBook) {
        BookDto foundBook = libraryService.findBookByBookTitle(titleBook);

        return localeService.getMessage("shell.book.all.information",
                new String[]{foundBook.getTitle(), foundBook.getAuthor(), foundBook.getGenre()});
    }

    @ShellMethod(key = {"db", "deleteBook"}, value = "Delete a book with all related information, by its \"Title\".")
    public String deleteBookWithAllInfoByTitle(@ShellOption String titleBook) {
        libraryService.deleteBookWithAllInfoByBookTitle(titleBook);
        return localeService.getMessage("shell.book.all.delete", new String[]{titleBook});
    }

    @ShellMethod(key = {"fab", "findAllBook"}, value = "Find all books in the library.")
    public List<String> findAllBooks() {
        List<BookDto> list = libraryService.findAllBooks();

        List<String> result = new ArrayList<>(list.size());

        result.add(localeService.getMessage("shell.book.all", null));
        result.addAll(list.stream()
                .map(b -> "title = " + b.getTitle() + ", author = " + b.getAuthor() + ", genre = " + b.getGenre() + ";")
                .collect(Collectors.toList())
        );

        return result;
    }

    @ShellMethod(key = {"fbr", "findBookReviews"}, value = "Find all reviews of a book by its \"title\".")
    public List<String> findBookReviewsByTitle(@ShellOption String bookTitle) {
        BookReviewDto foundReviews = libraryService.findBookReviewsByTitle(bookTitle);

        List<String> result = new ArrayList<>(foundReviews.getReviews().size());

        result.add(localeService.getMessage("shell.book.review.all", null));
        result.addAll(foundReviews.getReviews().stream()
                .map(r -> r + ";")
                .collect(Collectors.toList())
        );

        return result;
    }

    @ShellMethod(key = {"sbr", "saveBookReview"}, value = "Add a \"review\" about the book by its \"title\", please indicate its \"title book\" \"review\"")
    public String saveBookReview(@ShellOption String bookTitle, @ShellOption String review) {
        libraryService.saveReview(bookTitle, review);

        return localeService.getMessage("shell.book.review.add", new String[]{review});
    }
}
