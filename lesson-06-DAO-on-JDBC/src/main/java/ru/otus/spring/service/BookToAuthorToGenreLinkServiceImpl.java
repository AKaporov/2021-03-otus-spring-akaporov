package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookToAuthorToGenreLinkDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookToAuthorToGenreLink;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

/**
 * Сервси по работе с BookToAuthorToGenreLink
 */

@Service
@RequiredArgsConstructor
public class BookToAuthorToGenreLinkServiceImpl implements BookToAuthorToGenreLinkService {
    private final BookToAuthorToGenreLinkDao dao;

    @Override
    public BookToAuthorToGenreLink getByBookTitle(String bookTitle) {
        return dao.getByBookTitle(bookTitle).orElse(emptyLink());
    }

    private BookToAuthorToGenreLink emptyLink() {
        return new BookToAuthorToGenreLink(0,
                new Book(""),
                new Author("", "", ""),
                new Genre(""));
    }

    @Override
    public long insert(BookToAuthorToGenreLink link) {
        return dao.insert(link);
    }

    @Override
    public void deleteById(long id) {
        Optional<BookToAuthorToGenreLink> findLink = dao.getById(id);
        if (findLink.isPresent()) {
            dao.deleteById(id);
        }
    }

    @Override
    public List<BookToAuthorToGenreLink> getAllLink() {
        return dao.getAllLink();
    }
}
