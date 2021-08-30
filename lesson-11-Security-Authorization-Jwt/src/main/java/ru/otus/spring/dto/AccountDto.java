package ru.otus.spring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountDto {
    private final String name;
    private final String password;

    @Override
    public String toString() {
        return "AccountDto{name = " + this.getName() +
                ", password = " + this.getPassword() +
                "}";
    }
}
