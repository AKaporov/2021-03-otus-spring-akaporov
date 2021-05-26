package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

/**
 * Сервис по работе с жанрами книг
 */

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;

    @Override
    public Genre insert(Genre genre) {
        return dao.insert(genre);
    }

    @Override
    public Genre getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Genre genre) {
        dao.update(genre);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public Optional<Genre> getByName(String genreName) {
        return dao.getByName(genreName);
    }
}
