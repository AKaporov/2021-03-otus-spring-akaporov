package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.exception.QuestionDaoException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.parser.ParserInputStream;
import ru.otus.spring.parser.ParserQuestion;

import java.io.InputStream;
import java.util.List;

/**
 * Класс для работы с Dao вопросами
 */

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final String resourceFile;
    private final ParserInputStream parserInputStream;
    private final ParserQuestion parserQuestion;

    public QuestionDaoImpl(@Value("${file.question}") String resourceFile,
                           ParserInputStream parserInputStream,
                           ParserQuestion parserQuestion) {
        this.resourceFile = resourceFile;
        this.parserInputStream = parserInputStream;
        this.parserQuestion = parserQuestion;
    }

    @Override
    public List<Question> getQuestion() {
        InputStream streamFromResource = this.getClass().getClassLoader().getResourceAsStream(resourceFile);

        if (streamFromResource == null) {
            String exception = String.format("Resource file %s not found!", resourceFile);
            throw new QuestionDaoException(exception);
        }

        return getQuestionListFromStream(streamFromResource);
    }

    private List<Question> getQuestionListFromStream(InputStream streamFromResource) {
        List<String[]> arrayList = parserInputStream.parser(streamFromResource);
        return parserQuestion.parser(arrayList);
    }
}
