package spring.generator;

import ru.otus.spring.domain.Student;

public class UserGenerator {
    public static Student createUser(String name, String surname){
        return new Student(name, surname);
    }
}
