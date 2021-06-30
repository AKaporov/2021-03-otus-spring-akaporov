package ru.otus.spring.service;

import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;

import java.util.List;

public interface LibraryService {
    void insertBook(String titleBook, String authorName, String genreName);

    void deleteBookWithAllInfoByBookTitle(String titleBook);

    BookDto findBookByBookTitle(String titleBook);

    List<BookDto> findAllBooks();

    BookReviewDto findBookReviewsByTitle(String titleBook);

    void saveReview(String titleBook, String review);
}
