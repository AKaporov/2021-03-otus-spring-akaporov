package ru.otus.spring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.ResultStudentTest;
import ru.otus.spring.domain.User;
import ru.otus.spring.generator.UserGenerator;


@ExtendWith(MockitoExtension.class)
@DisplayName("Класс ResultStudentTestServiceImpl")
class ResultStudentTestServiceImplTest {
    private ResultStudentTestServiceImpl resultStudentTestService;

    @Mock
    private CommunicationService communicationService;

    @BeforeEach
    void setUp() {
        resultStudentTestService = new ResultStudentTestServiceImpl(communicationService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("должен ")
    void resultStudentTest() {
        ResultStudentTest result = createResultStudentTest();

        resultStudentTestService.resultStudentTest(result);

        String question = String.format("User %s %s answered %s questions out of %s correctly!",
                "name".toUpperCase(),
                "surname".toUpperCase(),
                5,
                5
        );

        Mockito.verify(communicationService, Mockito.times(1)).ask(question);
    }

    private ResultStudentTest createResultStudentTest() {
        User user = UserGenerator.createUser("name", "surname");
        return new ResultStudentTest(user, 5, 5);
    }
}