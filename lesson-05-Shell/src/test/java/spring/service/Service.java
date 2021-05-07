package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.domain.Student;
import ru.otus.spring.service.LocaleServiceImpl;
import spring.generator.CreateStudentGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс QuestionLocaleImpl")
class Service {
    private LocaleServiceImpl questionLocale;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        questionLocale = new LocaleServiceImpl(messageSource);
    }

    @Test
    @DisplayName("должен корректно локализовывать сообщение")
    void shouldCorrectLocale() {
        Student student = CreateStudentGenerator.createStudent("name", "surname");

        String expectedMessage = String.format("Student %s %s answered %d questions out of %d correctly!",
                student.getName().toUpperCase(),
                student.getSurname().toUpperCase(),
                5,
                5);
        when(questionLocale.getMessage("test.result.student-test.message",
                new String[]{student.getName().toUpperCase(),
                        student.getSurname().toUpperCase(),
                        "5",
                        "5"
                }
        )).thenReturn(expectedMessage);

        String actualMessage = questionLocale.getMessage("test.result.student-test.message",
                new String[]{student.getName().toUpperCase(),
                        student.getSurname().toUpperCase(),
                        "5",
                        "5"
                }
        );

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}