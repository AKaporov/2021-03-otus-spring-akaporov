package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Account;
import ru.otus.spring.dto.AccountDto;
import ru.otus.spring.jwt.JwtProvider;
import ru.otus.spring.service.AccountService;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final JwtProvider jwtProvider;
    private final AccountService accountService;

    @PostMapping(value = "/api/v1/token")
    public String getToken(@RequestBody(required = true) AccountDto accountDto) {
        Account account = accountService.findByNameAndPassword(accountDto.getName(), accountDto.getPassword());
        return jwtProvider.generateToken(account.getName());
    }
}
