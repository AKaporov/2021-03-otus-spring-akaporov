package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ResourceDao;
import ru.otus.spring.dao.exception.ResourceDaoException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.parser.ParserInputStream;
import ru.otus.spring.parser.ParserQuestion;

import java.io.InputStream;
import java.util.List;

/**
 * Класс реализующий работу с вопросами
 */

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final ResourceDao resourceDao;
    private final ParserInputStream parserInputStream;
    private final ParserQuestion parserQuestion;
    private final CommunicationService consoleService;

    @Override
    public List<Question> getAllQuestion() {
        InputStream streamFromResource = resourceDao.getInputStreamFromResource();
        List<String[]> streamList = parserInputStream.parser(streamFromResource);
        return parserQuestion.parser(streamList);
    }

    @Override
    public void askQuestion(Question question) {
        consoleService.ask(question.getQuestion());
        question.getAnswer().forEach((a) -> {
            consoleService.ask(a);
        });
    }
}
