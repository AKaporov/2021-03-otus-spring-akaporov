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
    private final LocaleService localeService;

    @Override
    public void resultStudentTest(ResultStudentTest result) {
        String resultTest = localeService.getMessage("result.student-test.message",
                new String[]{result.getStudent().getName().toUpperCase(),
                        result.getStudent().getSurname().toUpperCase(),
                        String.valueOf(result.getCountRightAnswer()),
                        String.valueOf(result.getCountQuestion())
                }
        );

        communicationService.ask(resultTest);
    }
}
