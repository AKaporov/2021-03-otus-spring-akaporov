package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;

/**
 * Класс реализующий работу с пользователем тестиования
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CommunicationService communicationService;
    private final QuestionLocale questionLocale;

    @Override
    public Student getUser() {
        return new Student(askUserName(), askUserSurname());
    }

    private String askUserSurname() {
        String name = questionLocale.getMessage("student.surname.message", null);
        communicationService.ask(name);
        return communicationService.getAnswer();
    }

    private String askUserName() {
        String surName = questionLocale.getMessage("student.name.message", null);
        communicationService.ask(surName);
        return communicationService.getAnswer();
    }
}
