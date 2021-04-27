package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

/**
 * Класс реализующий работу с пользователем тестиования
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CommunicationService communicationService;
    private final MessageSource messageSource;

    @Override
    public User getUser() {
        return new User(askUserName(), askUserSurname());
    }

    private String askUserSurname() {
        String name = messageSource.getMessage("user.surname.message", null, LocaleContextHolder.getLocale());
        communicationService.ask(name);
        return communicationService.getAnswer();
    }

    private String askUserName() {
        String surName = messageSource.getMessage("user.name.message", null, LocaleContextHolder.getLocale());
        communicationService.ask(surName);
        return communicationService.getAnswer();
    }
}
