package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.parser.ParserQuestion;

import java.util.List;

/**
 * Класс реализующий работу с вопросами
 */

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final ParserQuestion parserQuestion;
    private final CommunicationService communicationService;

    @Override
    public List<Question> getAllQuestion() {
        List<String[]> streamList = questionDao.getQuestion();
        return parserQuestion.parser(streamList);
    }

    @Override
    public void askQuestion(Question question) {
        communicationService.ask(question.getQuestion());
        question.getAnswer().forEach((a) -> {
            communicationService.ask(a);
        });
    }
}
