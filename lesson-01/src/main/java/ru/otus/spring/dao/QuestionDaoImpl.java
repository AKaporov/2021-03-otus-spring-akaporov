package ru.otus.spring.dao;

import au.com.bytecode.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Класс получения вопросов
 */

@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final String questionResourceFile;

    /**
     * Возвращает вопрос из файла, по указанному номеру {@code numberQuestion}.
     *
     * @param numberQuestion номер вопроса в файле.
     * @return вопрос в файле
     * @throws IOException если файл не найден.
     */
    @Override
    public String getQuestion(int numberQuestion) throws IOException {
        InputStream questionInputStream = this.getClass().getClassLoader().getResourceAsStream(questionResourceFile);

        if (questionInputStream == null) {
            String exception = new StringBuilder("File not found ")
                    .append(questionResourceFile)
                    .append("!")
                    .toString();
            throw new FileNotFoundException(exception);
        }

        CSVReader questionFile = new CSVReader(
                new InputStreamReader(questionInputStream), ';', '"', numberQuestion);
        String[] question = questionFile.readNext();

        return Arrays.toString(question);
    }
}
