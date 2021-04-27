package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.otus.spring.domain.ResultStudentTest;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.CommunicationService;
import ru.otus.spring.service.ResultStudentTestServiceImpl;
import spring.generator.UserGenerator;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс ResultStudentTestServiceImpl")
class ResultStudentTestServiceImplTest {
    private ResultStudentTestServiceImpl resultStudentTestService;

    @Mock
    private CommunicationService communicationService;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        resultStudentTestService = new ResultStudentTestServiceImpl(communicationService, messageSource);
    }

    @Test
    @DisplayName("должен ")
    void resultStudentTest() {
        ResultStudentTest result = createResultStudentTest();

        resultStudentTestService.resultStudentTest(result);

        String question = messageSource.getMessage("result.student-test.message",
                new String[]{result.getUser().getName().toUpperCase(),
                        result.getUser().getSurname().toUpperCase(),
                        String.valueOf(result.getCountRightAnswer()),
                        String.valueOf(result.getCountQuestion())
                }, LocaleContextHolder.getLocale());

        Mockito.verify(communicationService, Mockito.times(1)).ask(question);
    }

    private ResultStudentTest createResultStudentTest() {
        User user = UserGenerator.createUser("name", "surname");
        return new ResultStudentTest(user, 5, 5);
    }
}