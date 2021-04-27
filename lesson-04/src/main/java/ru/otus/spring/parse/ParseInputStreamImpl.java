package ru.otus.spring.parse;

import au.com.bytecode.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    private final MessageSource messageSource;

    @Override
    public List<String[]> parser(InputStream stream) {
        try (CSVReader questionFile = new CSVReader(new InputStreamReader(stream), ';', '"')) {

            return questionFile.readAll();

        } catch (Exception e) {
            String exception = messageSource.getMessage("exception.parse.message",
                    new String[]{e.getMessage()}, LocaleContextHolder.getLocale());

            throw new ParseException(exception);
        }
    }
}
