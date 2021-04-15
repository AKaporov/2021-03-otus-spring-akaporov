package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.User;

/**
 * Класс реализующий работу с пользователем тестиования
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CommunicationService communicationService;

    @Override
    public User getUser() {
        return new User(askUserName(), askUserSurname());
    }

    private String askUserSurname() {
        communicationService.ask("What is your last name?");
        return communicationService.getAnswer();
    }

    private String askUserName() {
        communicationService.ask("What is your name?");
        return communicationService.getAnswer();
    }
}
