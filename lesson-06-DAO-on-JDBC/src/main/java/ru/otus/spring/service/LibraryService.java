package ru.otus.spring.service;

import ru.otus.spring.domain.BookToAuthorToGenreLink;

import java.util.List;

public interface LibraryService {
    BookToAuthorToGenreLink getLinkByBookTitle(String bookTitle);

    boolean insertLinkWithAllInfo(String titleBook, String fullNameAuthor, String genre);

    void deleteLinkByBookTitle(String bookTitle);

    List<BookToAuthorToGenreLink> getAllLink();
}
