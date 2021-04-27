package ru.otus.spring.dao;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
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

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final String resourceFile;
    private final ParseInputStream parseInputStream;
    private final ParseQuestion parseQuestion;
    private final MessageSource messageSource;

    public QuestionDaoImpl(QuestionConfig questionConfig, ParseInputStream parseInputStream, ParseQuestion parseQuestion, MessageSource messageSource) {
        this.resourceFile = questionConfig.getCsvFileName();
        this.parseInputStream = parseInputStream;
        this.parseQuestion = parseQuestion;
        this.messageSource = messageSource;
    }

    @Override
    public List<Question> getQuestion() {
        InputStream streamFromResource = this.getClass().getClassLoader().getResourceAsStream(resourceFile);

        if (streamFromResource == null) {
            String exception = messageSource.getMessage("exception.questionDao.message",
                    new String[]{resourceFile}, LocaleContextHolder.getLocale());
            throw new QuestionDaoException(exception);
        }

        return getQuestionListFromStream(streamFromResource);
    }

    private List<Question> getQuestionListFromStream(InputStream streamFromResource) {
        List<String[]> arrayList = parseInputStream.parser(streamFromResource);
        return parseQuestion.parser(arrayList);
    }
}
