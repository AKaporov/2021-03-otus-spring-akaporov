package ru.otus.spring.service;

import ru.otus.spring.domain.Account;

public interface AccountService {
    Account findByNameAndPassword(String userName, String password);

    Account findByName(String userName);
}
