package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookToAuthorToGenreLink;
import ru.otus.spring.domain.Genre;

import java.util.List;

/**
 * Сервис по работе с библиотекой
 */

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private static final long EMPTY_ID = 0L;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookToAuthorToGenreLinkService service;

    @Override
    public BookToAuthorToGenreLink getLinkByBookTitle(String bookTitle) {
        return service.getByBookTitle(bookTitle);
    }

    @Override
    public boolean insertLinkWithAllInfo(String titleBook, String fullNameAuthor, String genreName) {
        long newLinkId = EMPTY_ID;
        Book newBook = checkAndInsertBook(titleBook);
        Author newAuthor = checkAndInsertAuthor(fullNameAuthor);
        Genre newGenre = checkAndInsertGenre(genreName);

        boolean existsInfo = (newBook.getId() != EMPTY_ID) && (newAuthor.getId() != EMPTY_ID) && (newGenre.getId() != EMPTY_ID);
        if (existsInfo) {
            newLinkId = service.insert(new BookToAuthorToGenreLink(newBook, newAuthor, newGenre));
        }

        return (newLinkId != EMPTY_ID);
    }

    @Override
    public void deleteLinkByBookTitle(String bookTitle) {
        BookToAuthorToGenreLink existsLink = service.getByBookTitle(bookTitle);

        if (existsLink.getId() == 0) {
            return;
        }

        genreService.deleteById(existsLink.getGenre().getId());
        authorService.deleteById(existsLink.getAuthor().getId());
        bookService.deleteById(existsLink.getBook().getId());

        service.deleteById(existsLink.getId());
    }

    @Override
    public List<BookToAuthorToGenreLink> getAllLink() {
        return service.getAllLink();
    }

    private Genre checkAndInsertGenre(String genreName) {
        Genre findGenre = genreService.getByName(genreName);
        if (findGenre.getId() == 0) {
            return createNewGenre(genreName);
        }
        return findGenre;
    }

    private Genre createNewGenre(String genreName) {
        Genre newGenre = new Genre(genreName);
        long newGenreId = genreService.insert(newGenre);
        newGenre.setId(newGenreId);
        return newGenre;
    }

    private Author checkAndInsertAuthor(String fullNameAuthor) {
        Author author = authorService.parserFullName(fullNameAuthor);

        Author findAuthor = authorService.getByFullName(author);
        if (findAuthor.getId() == EMPTY_ID) {
            return createNewAuthor(author);
        }
        return findAuthor;
    }

    private Author createNewAuthor(Author author) {
        long newAuthorId = authorService.insert(author);
        author.setId(newAuthorId);
        return author;
    }

    private Book checkAndInsertBook(String titleBook) {
        Book findBook = bookService.getByTitle(titleBook);

        if (findBook.getId() == EMPTY_ID) {
            return createNewBook(titleBook);
        }

        return findBook;
    }

    private Book createNewBook(String titleBook) {
        Book newBook = new Book(titleBook);
        long newBookId = bookService.insert(newBook);
        newBook.setId(newBookId);
        return newBook;
    }
}
