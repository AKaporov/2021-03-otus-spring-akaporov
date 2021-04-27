package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.exception.QuestionDaoException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.generator.CreateQuestionListGenerator;
import ru.otus.spring.parser.ParserInputStream;
import ru.otus.spring.parser.ParserQuestion;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс Dao вопросов")
class QuestionDaoImplTest {
    private QuestionDaoImpl dao;
    @Mock
    private ParserInputStream parserInputStream;
    @Mock
    private ParserQuestion parserQuestion;

    @Test
    @DisplayName("должен генерить исключение, если файла с вопросами не существует")
    void shouldQuestionDaoExceptionWhenFileNotFound() {
        dao = getQuestionDao("test.csv");

        Exception exception = assertThrows(QuestionDaoException.class, () -> {
            dao.getQuestion();
        });

        assertEquals(exception.getClass(), QuestionDaoException.class);
    }

    private QuestionDaoImpl getQuestionDao(String resourceFile) {
        return new QuestionDaoImpl(resourceFile, parserInputStream, parserQuestion);
    }

    @Test
    @DisplayName("должен получить список вопросов из файла ресурсов")
    void shouldGetListQuestionFromResourceFile() {
        dao = getQuestionDao("QuestionTest.csv");

        List<String[]> arrayList = new LinkedList<>();
        when(parserInputStream.parser(any())).thenReturn(arrayList);

        List<Question> questions = CreateQuestionListGenerator.createOneQuestionInList();
        when(parserQuestion.parser(any())).thenReturn(questions);

        List<Question> questionList = dao.getQuestion();

        assertAll(() -> {
            assertThat(questionList).isNotEmpty();
        });
    }
}