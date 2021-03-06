package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.ResultStudentTest;
import ru.otus.spring.domain.Student;

import java.util.List;

/**
 * Реализация интерфейса по проведению тестирования студентов.
 */

@Service
@RequiredArgsConstructor
public class StudentTestServiceImpl implements StudentTestService {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CheckService checkService;
    private final ResultStudentTestService resultService;

    /**
     * Начало тестирования.
     */
    @Override
    public void startTest(Student student) {
        int countRightAnswer = 0;

        List<Question> allQuestion = questionService.getAllQuestion();
        for (Question question : allQuestion) {
            questionService.askQuestion(question);
            String answer = answerService.getAnswer();
            if (checkService.check(question.getRightAnswer(), answer)) {
                countRightAnswer++;
            }
        }

        resultService.resultStudentTest(new ResultStudentTest(student, allQuestion.size(), countRightAnswer));
    }
}
