package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Класс для конфигурации сервиса локализации
 */

@Component
@RequiredArgsConstructor
public class LocaleServiceImpl implements LocaleService {
    private final MessageSource messageSource;

    @Override
    public String getMessage(String bundleName, @Nullable Object[] args) {
        return messageSource.getMessage(bundleName, args, LocaleContextHolder.getLocale());
    }
}
