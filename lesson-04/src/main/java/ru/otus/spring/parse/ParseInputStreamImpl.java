package ru.otus.spring.parse;

import au.com.bytecode.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.parse.exception.ParseException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Класс для преобразования потока в список массивов строк.
 */

@Component
@RequiredArgsConstructor
public class ParseInputStreamImpl implements ParseInputStream {
    @Override
    public List<String[]> parser(InputStream stream) {
        try (CSVReader questionFile = new CSVReader(new InputStreamReader(stream), ';', '"')) {

            return questionFile.readAll();

        } catch (Exception e) {
            throw new ParseException(String.format("Error parser CSV file! " + e.getMessage()));
        }
    }
}
