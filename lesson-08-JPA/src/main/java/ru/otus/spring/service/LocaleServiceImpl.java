package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocaleServiceImpl implements LocaleService {
    private final MessageSource messageSource;

    @Override
    public String getMessage(String bundleName, Object[] args) {
        return messageSource.getMessage(bundleName, args, LocaleContextHolder.getLocale());
    }
}
