package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с книгами
 */

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao dao;

    @Override
    public Book insertAll(Book book) {
        return dao.insertAll(book);
    }

    @Override
    public Book getAllById(long id) {
        return dao.getAllById(id);
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Book book) {
        dao.update(book);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public Optional<Book> getAllByTitle(String bookTitle) {
        return dao.getAllByTitle(bookTitle);
    }

    @Override
    public Book getById(long id) {
        return dao.getById(id);
    }
}
