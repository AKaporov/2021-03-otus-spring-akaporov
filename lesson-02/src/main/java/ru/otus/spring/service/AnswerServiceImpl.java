package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс по работе с ответами
 */

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final CommunicationService consoleService;

    @Override
    public String getAnswer() {
        return consoleService.getAnswer();
    }
}
