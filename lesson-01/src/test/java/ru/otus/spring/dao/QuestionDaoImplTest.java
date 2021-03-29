package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Вопросы для теста")
class QuestionDaoImplTest {

    private QuestionDaoImpl questions;

    @Test
    @DisplayName("должны генерить исключение, если файла не существует")
    void shouldIOExceptionWhenFileNotFound() {
        String questionFile = "test.csv";
        Exception exception = assertThrows(IOException.class, () -> {
            questions = new QuestionDaoImpl(questionFile);
            questions.getQuestion(0);
        });

        String expectedException = new StringBuilder("File not found ")
                .append(questionFile)
                .append("!")
                .toString();
        String actualException = exception.getMessage();

        assertTrue(actualException.contains(expectedException));
    }
}