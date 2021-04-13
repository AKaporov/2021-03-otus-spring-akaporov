package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.ResultStudentTest;

/**
 * Класс для подведения итого тестирования
 */
@Service
@RequiredArgsConstructor
public class ResultStudentTestServiceImpl implements ResultStudentTestService {
    private final CommunicationService consoleService;

    @Override
    public void resultStudentTest(ResultStudentTest result) {
        String resultTest = new StringBuilder("User ")
                .append(result.getUser().getSurname())
                .append(" ")
                .append(result.getUser().getName())
                .append(" answered ")
                .append(result.getCountRightAnswer())
                .append(" questions out of ")
                .append(result.getCountQuestion())
                .append(" correctly!")
                .toString();

        consoleService.ask(resultTest);
    }
}
