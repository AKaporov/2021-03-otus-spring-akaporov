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
    public long insert(Book book) {
        return dao.insert(book);
    }

    @Override
    public Book getById(long id) {
        return dao.getById(id).orElse(emptyBook());
    }

    private Book emptyBook() {
        return new Book("");
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
        Optional<Book> findBook = dao.getById(id);
        if (findBook.isPresent()) {
            dao.deleteById(id);
        }
    }

    @Override
    public Book getByTitle(String titleBook) {
        return dao.getByTitle(titleBook).orElse(emptyBook());
    }
}
