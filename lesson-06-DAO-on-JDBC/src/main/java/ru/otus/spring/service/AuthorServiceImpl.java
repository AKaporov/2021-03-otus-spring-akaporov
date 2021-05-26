package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с авторами книг
 */

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao dao;

    @Override
    public Author insert(Author author) {
        return dao.insert(author);
    }

    @Override
    public Author getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Author author) {
        dao.update(author);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return dao.getByName(name);
    }
}
