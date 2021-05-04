package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.config.QuestionConfig;
import ru.otus.spring.dao.exception.QuestionDaoException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.parse.ParseInputStream;
import ru.otus.spring.parse.ParseQuestion;

import java.io.InputStream;
import java.util.List;

/**
 * Класс для работы с Dao вопросами
 */

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private final String resourceFile;
    private final ParseInputStream parseInputStream;
    private final ParseQuestion parseQuestion;

    public QuestionDaoImpl(QuestionConfig questionConfig, ParseInputStream parseInputStream, ParseQuestion parseQuestion) {
        this.resourceFile = questionConfig.getCsvFileName();
        this.parseInputStream = parseInputStream;
        this.parseQuestion = parseQuestion;
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
        List<String[]> arrayList = parseInputStream.parser(streamFromResource);
        return parseQuestion.parser(arrayList);
    }
}
