package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.ResultStudentTest;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.*;
import spring.generator.CreateQuestionListGenerator;
import spring.generator.CreateStudentGenerator;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс StudentTestServiceImpl")
class StudentTestServiceImplTest {
    private StudentTestServiceImpl studentTestService;
    @Mock
    private QuestionService questionService;
    @Mock
    private StudentService studentService;
    @Mock
    private AnswerService answerService;
    @Mock
    private CheckService checkService;
    @Mock
    private ResultStudentTestService resultService;

    @BeforeEach
    void setUp() {
        studentTestService = new StudentTestServiceImpl(questionService, studentService, answerService, checkService, resultService);
    }

    @Test
    @DisplayName("должен проводить тестирование студента")
    void shouldTestStudent() {
        Student student = CreateStudentGenerator.createStudent("name", "surname");
        when(studentService.getStudent()).thenReturn(student);
        List<Question> allQuestion = CreateQuestionListGenerator.createOneQuestionInList();
        when(questionService.getAllQuestion()).thenReturn(allQuestion);
        String answer = "B";
        when(answerService.getAnswer()).thenReturn(answer);
        when(checkService.check(allQuestion.get(0).getRightAnswer(), answer)).thenReturn(true);

        studentTestService.startTest();

        verify(resultService).resultStudentTest(any(ResultStudentTest.class));
    }
}