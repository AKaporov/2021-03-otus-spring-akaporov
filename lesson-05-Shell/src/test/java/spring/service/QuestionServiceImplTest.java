package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.CommunicationService;
import ru.otus.spring.service.QuestionServiceImpl;
import spring.generator.CreateQuestionListGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс QuestionServiceImpl")
class QuestionServiceImplTest {
    private QuestionServiceImpl questionService;
    @Mock
    private QuestionDao questionDao;
    @Mock
    private CommunicationService communicationService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionDao, communicationService);
    }

    @Test
    @DisplayName("должен получить список вопросов")
    void shouldReturnQuestionList() {
        List<Question> questionList = CreateQuestionListGenerator.createOneQuestionInList();
        when(questionDao.getQuestion()).thenReturn(questionList);

        List<Question> allQuestion = questionService.getAllQuestion();

        assertEquals(questionList, allQuestion);
    }

    @Test
    @DisplayName("должен задавать вопрос и предлагать варианты ответов")
    void shouldAskQuestionAndShowAnswer() {
        Question question = CreateQuestionListGenerator.createOneQuestionInList().get(0);

        questionService.askQuestion(question);

        verify(communicationService).ask(question.getQuestion());
    }
}