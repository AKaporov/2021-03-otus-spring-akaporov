package ru.otus.spring.jwt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtProviderException extends RuntimeException {
    private final String message;
}
