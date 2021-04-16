package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.exception.ResourceDaoException;
import ru.otus.spring.parser.ParserInputStream;

import java.io.InputStream;
import java.util.List;

/**
 * Класс для работы с Dao вопросами
 */

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final String resourceFile;
    private final ParserInputStream parserInputStream;

    public QuestionDaoImpl(@Value("${file.question}") String resourceFile, ParserInputStream parserInputStream) {
        this.resourceFile = resourceFile;
        this.parserInputStream = parserInputStream;
    }

    @Override
    public List<String[]> getQuestion() {
        InputStream streamFromResource = this.getClass().getClassLoader().getResourceAsStream(resourceFile);

        if (streamFromResource == null) {
            String exception = String.format("Resource file '%s'not found!", resourceFile);
            throw new ResourceDaoException(exception);
        }

        return parserInputStream.parser(streamFromResource);
    }
}
