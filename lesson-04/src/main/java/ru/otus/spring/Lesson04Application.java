package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.service.StudentTestService;

@SpringBootApplication
public class Lesson04Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Lesson04Application.class, args);

        StudentTestService studentTestService = context.getBean(StudentTestService.class);
        studentTestService.startTest();
        context.close();
    }

}
