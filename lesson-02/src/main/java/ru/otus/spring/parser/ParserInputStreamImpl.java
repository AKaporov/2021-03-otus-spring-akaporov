package ru.otus.spring.parser;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.stereotype.Component;
import ru.otus.spring.parser.exception.ParserException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Класс для преобразования потока в список массивов строк.
 */
@Component
public class ParserInputStreamImpl implements ParserInputStream {
    @Override
    public List<String[]> parser(InputStream stream) {
        try (CSVReader questionFile = new CSVReader(new InputStreamReader(stream), ';', '"')) {

            return questionFile.readAll();

        } catch (Exception e) {
            String exception = new StringBuilder("Error parser CSV! ")
                    .append(e.getMessage())
                    .toString();

            throw new ParserException(exception);
        }
    }
}
