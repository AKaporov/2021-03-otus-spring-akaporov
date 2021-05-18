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
    private final ParseFullNameAuthorService parse;

    @Override
    public long insert(Author author) {
        return dao.insert(author);
    }

    @Override
    public Author getById(long id) {
        return dao.getById(id).orElse(emptyAuthor());
    }

    private Author emptyAuthor() {
        return new Author("", "", "");
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
        Optional<Author> findAuthor = dao.getById(id);
        if (findAuthor.isPresent()) {
            dao.deleteById(id);
        }
    }

    @Override
    public Author getByFullName(Author author) {
        return dao.getByFullName(author).orElse(emptyAuthor());
    }

    @Override
    public Author parserFullName(String fullNameAuthor) {
        return parse.parser(fullNameAuthor);
    }
}
