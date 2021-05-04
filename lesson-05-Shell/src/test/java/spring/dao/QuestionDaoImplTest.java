package spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.config.QuestionConfig;
import ru.otus.spring.dao.QuestionDaoImpl;
import ru.otus.spring.dao.exception.QuestionDaoException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.parse.ParseInputStream;
import ru.otus.spring.parse.ParseQuestion;
import spring.generator.CreateQuestionListGenerator;

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
    private ParseInputStream parseInputStream;
    @Mock
    private ParseQuestion parseQuestion;

    @Test
    @DisplayName("должен генерить исключение, если файла с вопросами не существует")
    void shouldQuestionDaoExceptionWhenFileNotFound() {
        QuestionConfig questionConfig = new QuestionConfig();
        questionConfig.setCsvFileName("test.csv");
        dao = getQuestionDao(questionConfig);

        Exception exception = assertThrows(QuestionDaoException.class, () -> {
            dao.getQuestion();
        });

        assertEquals(exception.getClass(), QuestionDaoException.class);
    }

    private QuestionDaoImpl getQuestionDao(QuestionConfig questionConfig) {
        return new QuestionDaoImpl(questionConfig, parseInputStream, parseQuestion);
    }

    @Test
    @DisplayName("должен получить список вопросов из файла ресурсов")
    void shouldGetListQuestionFromResourceFile() {
        QuestionConfig questionConfig = new QuestionConfig();
        questionConfig.setCsvFileName("QuestionTest.csv");
        dao = getQuestionDao(questionConfig);

        List<String[]> arrayList = new LinkedList<>();
        when(parseInputStream.parser(any())).thenReturn(arrayList);

        List<Question> questions = CreateQuestionListGenerator.createOneQuestionInList();
        when(parseQuestion.parser(any())).thenReturn(questions);

        List<Question> questionList = dao.getQuestion();

        assertAll(() -> {
            assertThat(questionList).isNotEmpty();
        });
    }
}