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
    public long insert(Genre genre) {
        return dao.insert(genre);
    }

    @Override
    public Genre getById(long id) {
        return dao.getById(id).orElse(emptyGenre());
    }

    private Genre emptyGenre() {
        return new Genre("");
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
        Optional<Genre> findGenre = dao.getById(id);
        if (findGenre.isPresent()) {
            dao.deleteById(id);
        }
    }

    @Override
    public Genre getByName(String genreName) {
        return dao.getByName(genreName).orElse(emptyGenre());
    }
}
