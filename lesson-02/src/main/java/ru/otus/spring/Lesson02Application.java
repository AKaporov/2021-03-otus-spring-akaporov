package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.StudentTestService;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Lesson02Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Lesson02Application.class);
        StudentTestService studentTestService = context.getBean(StudentTestService.class);
        studentTestService.startTest();
        context.close();
    }
}