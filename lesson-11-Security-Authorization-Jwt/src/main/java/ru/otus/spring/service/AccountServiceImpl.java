package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Account;
import ru.otus.spring.repositories.AccountRepository;
import ru.otus.spring.service.exception.EncodePasswordException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private static final String ACCOUNT_NOT_FOUND_ERROR = "Пользователь %s не найдет!";
    private static final String ENCODE_PASSWORD_DOES_NOT_LIKE_BCRYPT_ERROR = "Закодированный пароль не похож на BCrypt";
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account findByNameAndPassword(String userName, String password) {
        Account account = findByName(userName);

        return passwordEncoder(password, account)
                .orElseThrow(() -> new EncodePasswordException(ENCODE_PASSWORD_DOES_NOT_LIKE_BCRYPT_ERROR));
    }

    private Optional<Account> passwordEncoder(String password, Account foundAccount) {
        if (passwordEncoder.matches(password, foundAccount.getPassword())) {
            return Optional.ofNullable(foundAccount);
        }

        return Optional.empty();
    }

    @Override
    public Account findByName(String userName) {
        return repository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ACCOUNT_NOT_FOUND_ERROR, userName)));
    }
}
