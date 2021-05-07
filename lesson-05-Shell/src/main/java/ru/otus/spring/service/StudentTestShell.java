package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Student;

@ShellComponent
@RequiredArgsConstructor
public class StudentTestShell {
    private final StudentTestService studentTestService;
    private final LocaleService localeService;
    private Student student;

    @ShellMethod(value = "Let's start testing!", key = {"start", "s"})
    @ShellMethodAvailability(value = "isStudentAvailable")
    public void startStudentTest() {
        studentTestService.startTest(student);
    }

    @ShellMethod(value = "Introduce yourself as your first and last name", key = {"introduce", "i"})
    public String getStudent(@ShellOption String name, @ShellOption String surName) {
        student = new Student(name, surName);
        return localeService.getMessage("shell.welcome", new String[]{student.getName(), student.getSurname()});
    }

    private Availability isStudentAvailable() {
        return student != null ? Availability.available() : Availability.unavailable(localeService.getMessage("shell.introduce", null));
    }
}
