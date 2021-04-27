package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.ResultStudentTest;
import ru.otus.spring.domain.User;
import ru.otus.spring.generator.CreateQuestionListGenerator;
import ru.otus.spring.generator.UserGenerator;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс StudentTestServiceImpl")
class StudentTestServiceImplTest {
    private StudentTestServiceImpl studentTestService;
    @Mock
    private QuestionService questionService;
    @Mock
    private UserService userService;
    @Mock
    private AnswerService answerService;
    @Mock
    private CheckService checkService;
    @Mock
    private ResultStudentTestService resultService;

    @BeforeEach
    void setUp() {
        studentTestService = new StudentTestServiceImpl(questionService, userService, answerService, checkService, resultService);
    }

    @Test
    @DisplayName("должен проводить тестирование студента")
    void shouldTestStudent() {
        User user = UserGenerator.createUser("name", "surname");
        when(userService.getUser()).thenReturn(user);
        List<Question> allQuestion = CreateQuestionListGenerator.createOneQuestionInList();
        when(questionService.getAllQuestion()).thenReturn(allQuestion);
        String answer = "B";
        when(answerService.getAnswer()).thenReturn(answer);
        when(checkService.check(allQuestion.get(0).getRightAnswer(), answer)).thenReturn(true);

        studentTestService.startTest();

        verify(resultService).resultStudentTest(any(ResultStudentTest.class));
    }
}