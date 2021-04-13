package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.exception.ResourceDaoException;

import java.io.InputStream;

/**
 * Класс для работы с файлом-ресурсов.
 */

@Component
public class ResourceFileDaoImpl implements ResourceDao {
    private final String resourceFile;

    public ResourceFileDaoImpl(@Value("${file.question}") String resourceFile) {
        this.resourceFile = resourceFile;
    }

    @Override
    public InputStream getInputStreamFromResource() {
        InputStream result = this.getClass().getClassLoader().getResourceAsStream(resourceFile);

        if (result == null) {
            String exception = new StringBuilder("Resource file '")
                    .append(resourceFile)
                    .append("' not found! ")
                    .toString();
            throw new ResourceDaoException(exception);
        }

        return result;
    }
}
