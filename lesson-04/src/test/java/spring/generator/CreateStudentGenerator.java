package spring.generator;

import ru.otus.spring.domain.Student;

public class CreateStudentGenerator {
    public static Student createStudent(String name, String surname){
        return new Student(name, surname);
    }
}
