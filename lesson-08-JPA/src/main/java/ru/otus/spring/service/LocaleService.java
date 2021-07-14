package ru.otus.spring.service;

import org.springframework.lang.Nullable;

public interface LocaleService {
    String getMessage(String bundleName, @Nullable Object[] args);
}
