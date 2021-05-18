package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;

@Service
public class ParseFullNameAuthorServiceImpl implements ParseFullNameAuthorService {

    @Override
    public Author parser(String fullName) {
        String[] arrayAuthor = fullName.split(" ");
        return new Author(0L, arrayAuthor[0], arrayAuthor[1], arrayAuthor[2]);
    }
}
