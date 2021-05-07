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
import ru.otus.spring.service.StudentServiceImpl;
import ru.otus.spring.service.LocaleService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс StudentServiceImpl")
class StudentServiceImplTest {
    private StudentServiceImpl studentService;
    @Mock
    private CommunicationService communicationService;
    @Mock
    private LocaleService localeService;


    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(communicationService, localeService);
    }

    @Test
    @DisplayName("должен возвращать Фамилию и Имя проверяемого студента")
    void getStudent() {
        Student expectedStudent = new Student("name", "surname");
        Mockito.when(communicationService.getAnswer())
                .thenReturn(expectedStudent.getName())
                .thenReturn(expectedStudent.getSurname());

        Student student = studentService.getStudent();

        assertThat(student).usingRecursiveComparison().isEqualTo(expectedStudent);
    }
}