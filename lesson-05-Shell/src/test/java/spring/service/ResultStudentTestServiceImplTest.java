package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.ResultStudentTest;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.CommunicationService;
import ru.otus.spring.service.LocaleService;
import ru.otus.spring.service.ResultStudentTestServiceImpl;
import spring.generator.CreateStudentGenerator;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс ResultStudentTestServiceImpl")
class ResultStudentTestServiceImplTest {
    private ResultStudentTestServiceImpl resultStudentTestService;

    @Mock
    private CommunicationService communicationService;
    @Mock
    private LocaleService localeService;

    @BeforeEach
    void setUp() {
        resultStudentTestService = new ResultStudentTestServiceImpl(communicationService, localeService);
    }

    @Test
    @DisplayName("должен проводить тестирование")
    void resultStudentTest() {
        ResultStudentTest result = createResultStudentTest();

        String question = String.format("Student %s %s answered %s questions out of %s correctly!",
                result.getStudent().getName().toUpperCase(),
                result.getStudent().getSurname().toUpperCase(),
                String.valueOf(result.getCountRightAnswer()),
                String.valueOf(result.getCountQuestion())
        );
        when(localeService.getMessage("result.student-test.message",
                new String[]{result.getStudent().getName().toUpperCase(),
                        result.getStudent().getSurname().toUpperCase(),
                        String.valueOf(result.getCountRightAnswer()),
                        String.valueOf(result.getCountQuestion())
                }
        )).thenReturn(question);

        resultStudentTestService.resultStudentTest(result);

        Mockito.verify(communicationService, Mockito.times(1)).ask(question);
    }

    private ResultStudentTest createResultStudentTest() {
        Student student = CreateStudentGenerator.createStudent("name", "surname");
        return new ResultStudentTest(student, 5, 5);
    }
}