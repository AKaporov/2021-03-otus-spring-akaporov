package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с библиотекой
 */

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;


    private Genre checkAndInsertGenre(String genreName) {
        Optional<Genre> foundGenre = genreService.getByName(genreName);

        return foundGenre.orElseGet(() -> createNewGenre(genreName));
    }

    private Genre createNewGenre(String genreName) {
        return genreService.insert(new Genre(genreName));
    }

    private Author checkAndInsertAuthor(String authorName) {
        Optional<Author> foundAuthor = authorService.getByName(authorName);

        return foundAuthor.orElseGet(() -> createNewAuthor(authorName));
    }

    private Author createNewAuthor(String authorName) {
        return authorService.insert(new Author(authorName));
    }

    private Book checkAndInsertBook(String titleBook, Author newAuthor, Genre newGenre) {
        Book newBook = new Book(titleBook, newAuthor, newGenre);

        Optional<Book> foundBookWithAllInfo = bookService.getAllByTitle(titleBook);
        if (foundBookWithAllInfo.isPresent()) {
            if (foundBookWithAllInfo.get().getAuthor().getId() != newAuthor.getId() || foundBookWithAllInfo.get().getGenre().getId() != newGenre.getId()) {
                return createNewBook(newBook);
            }
            return foundBookWithAllInfo.get();
        }

        return createNewBook(newBook);

    }

    private Book createNewBook(Book newBook) {
        return bookService.insertAll(newBook);
    }

    @Override
    public Book gelBookAllInfoByBookTitle(String bookTitle) {
        return bookService.getAllByTitle(bookTitle).orElseThrow(() -> new ServiceException(String.format("The book {%s} was not found. Check the request details.", bookTitle)));
    }

    @Override
    public List<Book> getAllBookInfo() {
        return bookService.getAll();
    }

    @Override
    public Book insertBookWithAllInfo(String titleBook, String authorName, String genreName) {
        Genre newGenre = checkAndInsertGenre(genreName);
        Author newAuthor = checkAndInsertAuthor(authorName);
        return checkAndInsertBook(titleBook, newAuthor, newGenre);
    }

    @Override
    public void deleteBookWithAllInfoByBookTitle(String bookTitle) {
        Book foundBook = gelBookAllInfoByBookTitle(bookTitle);

        genreService.deleteById(foundBook.getGenre().getId());
        authorService.deleteById(foundBook.getAuthor().getId());
        bookService.deleteById(foundBook.getId());
    }
}
