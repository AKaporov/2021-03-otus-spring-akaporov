package ru.otus.spring.dao;

import java.io.InputStream;

/**
 * Интерфейс для работы с ресурсами
 */

public interface ResourceDao {
    InputStream getInputStreamFromResource();
}
