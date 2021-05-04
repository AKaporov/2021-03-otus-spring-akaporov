package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import ru.otus.spring.domain.Student;
import spring.generator.CreateStudentGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@DisplayName("Тест команд shell")
class StudentTestShellTest {

    private static final String GREETING_PATTERN = "Добро пожаловать %s %s, все готово для тестирования!";
    private static final String COMMAND_GET_STUDENT = "introduce";
    private static final String COMMAND_GET_STUDENT_SHORT = "i";
    private static final String IVANOV = "Иванов";
    private static final String IVAN = "Иван";
    private static final String COMMAND_LOGIN_PATTERN = "%s %s %s";

    @Autowired
    private Shell shell;

    @Test
    @DisplayName(" должен возвращать приветствие для всех форм команды Представьтесь")
    void shouldReturnExpectedStudentAfterGetStudentCommandEvaluated() {
        Student expectedStudent = CreateStudentGenerator.createStudent(IVAN, IVANOV);

        String actualWelcome = (String) shell.evaluate(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_GET_STUDENT, IVAN, IVANOV));
        String actualWelcomeShort = (String) shell.evaluate(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_GET_STUDENT_SHORT, IVAN, IVANOV));

        assertAll(() -> {
            assertThat(actualWelcome).isEqualTo(String.format(GREETING_PATTERN, expectedStudent.getName(), expectedStudent.getSurname()));
            assertThat(actualWelcomeShort).isEqualTo(String.format(GREETING_PATTERN, expectedStudent.getName(), expectedStudent.getSurname()));
        });
    }
}