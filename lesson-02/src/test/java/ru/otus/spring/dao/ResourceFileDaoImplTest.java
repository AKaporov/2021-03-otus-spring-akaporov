package ru.otus.spring.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.exception.ResourceDaoException;
import ru.otus.spring.parser.ParserInputStream;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Dao ресурсов")
class ResourceFileDaoImplTest {
    private QuestionDaoImpl dao;
    private ParserInputStream parserInputStream;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("должен генерить исключение, если файла не существует")
    void shouldResourceDaoExceptionWhenFileNotFound() {
        dao = getResourceDao("test.csv");

        Exception exception = assertThrows(ResourceDaoException.class, () -> {
            dao.getQuestion();
        });

        assertEquals(exception.getClass(), ResourceDaoException.class);
    }

    private QuestionDaoImpl getResourceDao(String resourceFile) {
        return new QuestionDaoImpl(resourceFile, parserInputStream);
    }
}