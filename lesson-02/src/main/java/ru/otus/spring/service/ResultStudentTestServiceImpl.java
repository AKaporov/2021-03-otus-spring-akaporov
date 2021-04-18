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
    private final CommunicationService communicationService;

    @Override
    public void resultStudentTest(ResultStudentTest result) {
        String resultTest = String.format("User %s %s answered %s questions out of %s correctly!",
                result.getUser().getName().toUpperCase(),
                result.getUser().getName().toUpperCase(),
                result.getCountRightAnswer(),
                result.getCountQuestion()
        );

        communicationService.ask(resultTest);
    }
}
