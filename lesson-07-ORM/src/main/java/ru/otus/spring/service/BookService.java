package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;

import java.util.List;

public interface BookService {
    BookDto findBookByTitle(String title);

    List<BookDto> findAllBook();

    BookReviewDto findBookReviewsByTitle(String bookTitle);

    Book save(BookDto bookDto);

    void remove(BookDto bookDto);

    void saveReview(String bookTitle, String review);
}
