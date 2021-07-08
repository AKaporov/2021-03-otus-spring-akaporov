package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;

import java.util.List;

/**
 * Сервис по работе с библиотекой
 */

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookService bookService;

    @Override
    public void insertBook(String titleBook, String authorName, String genreName) {
        bookService.save(new BookDto(titleBook, authorName, genreName));
    }

    @Override
    public void deleteBookWithAllInfoByBookTitle(String titleBook) {
        BookDto foundBook = bookService.findBookByTitle(titleBook);
        bookService.remove(new BookDto(foundBook.getTitle(), foundBook.getAuthor(), foundBook.getGenre()));
    }

    @Override
    public BookDto findBookByBookTitle(String titleBook) {
        return bookService.findBookByTitle(titleBook);
    }

    @Override
    public List<BookDto> findAllBooks() {
        return bookService.findAllBook();
    }

    @Override
    public BookReviewDto findBookReviewsByTitle(String titleBook) {
        return bookService.findBookReviewsByTitle(titleBook);
    }

    @Override
    public void saveReview(String titleBook, String review) {
        bookService.saveReview(titleBook, review);
    }
}
