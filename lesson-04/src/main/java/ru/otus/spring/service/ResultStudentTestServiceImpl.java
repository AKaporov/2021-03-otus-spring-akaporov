package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.ResultStudentTest;

/**
 * Класс для подведения итого тестирования
 */

@Service
@RequiredArgsConstructor
public class ResultStudentTestServiceImpl implements ResultStudentTestService {
    private final CommunicationService communicationService;
    private final MessageSource messageSource;

    @Override
    public void resultStudentTest(ResultStudentTest result) {
        String resultTest = messageSource.getMessage("result.student-test.message",
                new String[]{result.getUser().getName().toUpperCase(),
                        result.getUser().getSurname().toUpperCase(),
                        String.valueOf(result.getCountRightAnswer()),
                        String.valueOf(result.getCountQuestion())
                }, LocaleContextHolder.getLocale());
//        String.format("User %s %s answered %s questions out of %s correctly!",
//                result.getUser().getName().toUpperCase(),
//                result.getUser().getSurname().toUpperCase(),
//                result.getCountRightAnswer(),
//                result.getCountQuestion()
//        );

        communicationService.ask(resultTest);
    }
}
