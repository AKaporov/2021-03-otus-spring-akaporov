package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.TestingStudents;

import java.io.IOException;

public class Lesson01Application {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestingStudents testingStudents = context.getBean(TestingStudents.class);
        testingStudents.startTesting();
        context.close();
    }
}