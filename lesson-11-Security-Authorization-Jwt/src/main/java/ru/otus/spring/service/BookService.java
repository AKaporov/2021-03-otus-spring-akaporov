package ru.otus.spring.service;

import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;

import java.util.List;

public interface BookService {
    BookDto findBookByTitle(String title);

    List<BookDto> findAllBooks();

    BookReviewDto findBookReviewsByTitle(String bookTitle);

    BookDto save(BookDto bookDto);

    void remove(BookDto bookDto);

    void saveReview(String bookTitle, String review);
}
