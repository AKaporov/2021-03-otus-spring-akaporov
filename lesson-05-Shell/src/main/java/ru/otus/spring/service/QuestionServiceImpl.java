package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;

/**
 * Класс реализующий работу с вопросами
 */

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final CommunicationService communicationService;

    @Override
    public List<Question> getAllQuestion() {
        return questionDao.getQuestion();
    }

    @Override
    public void askQuestion(Question question) {
        communicationService.ask(question.getQuestion());
        question.getAnswer().forEach(communicationService::ask);
    }
}
