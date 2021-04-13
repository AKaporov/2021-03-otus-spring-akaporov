package ru.otus.spring.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.exception.ResourceDaoException;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Dao ресурсов")
class ResourceFileDaoImplTest {
    private ResourceFileDaoImpl dao;

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
            dao.getInputStreamFromResource();
        });

        assertEquals(exception.getClass(), ResourceDaoException.class);
    }

    private ResourceFileDaoImpl getResourceDao(String resourceFile) {
        return new ResourceFileDaoImpl(resourceFile);
    }

    @Test
    @DisplayName("дожен возвращать stream, если файл существует")
    void shouldGetInputStreamWhenFileFound() {
        dao = getResourceDao("Questions.csv");

        InputStream stream = dao.getInputStreamFromResource();

        assertNotNull(stream);
    }
}