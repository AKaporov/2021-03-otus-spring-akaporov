package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface LibraryService {
    Book gelBookAllInfoByBookTitle(String bookTitle);

    List<Book> getAllBookInfo();

    Book insertBookWithAllInfo(String titleBook, String authorName, String genreName);

    void deleteBookWithAllInfoByBookTitle(String bookTitle);
}
