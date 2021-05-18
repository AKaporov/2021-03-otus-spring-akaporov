package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

public interface ParseFullNameAuthorService {
    Author parser(String fullName);
}
