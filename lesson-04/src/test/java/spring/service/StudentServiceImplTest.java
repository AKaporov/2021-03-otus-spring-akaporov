package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.CommunicationService;
import ru.otus.spring.service.QuestionLocale;
import ru.otus.spring.service.UserServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс UserServiceImpl")
class StudentServiceImplTest {
    private UserServiceImpl userService;
    @Mock
    private CommunicationService communicationService;
    @Mock
    private QuestionLocale questionLocale;


    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(communicationService, questionLocale);
    }

    @Test
    @DisplayName("должен возвращать Фамилию и Имя проверяемого пользователя")
    void getUser() {
        Student expectedStudent = new Student("name", "surname");
        Mockito.when(communicationService.getAnswer())
                .thenReturn(expectedStudent.getName())
                .thenReturn(expectedStudent.getSurname());

        Student student = userService.getUser();

        assertThat(student).usingRecursiveComparison().isEqualTo(expectedStudent);
    }
}