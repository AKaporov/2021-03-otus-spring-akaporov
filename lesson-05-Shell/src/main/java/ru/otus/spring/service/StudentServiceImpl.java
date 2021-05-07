package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;

/**
 * Класс реализующий работу со студеном
 */

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final CommunicationService communicationService;
    private final LocaleService localeService;

    @Override
    public Student getStudent() {
        return new Student(askStudentName(), askStudentSurname() );
    }

    private String askStudentSurname() {
        String name = localeService.getMessage("student.surname.message", null);
        communicationService.ask(name);
        return communicationService.getAnswer();
    }

    private String askStudentName() {
        String surName = localeService.getMessage("student.name.message", null);
        communicationService.ask(surName);
        return communicationService.getAnswer();
    }
}
