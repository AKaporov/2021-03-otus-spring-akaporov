package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.exception.CommunicationConsoleException;

import java.io.*;

/**
 * Класс для реализации общения с тестируемым через консоль
 */

@Service
public class CommunicationConsoleServiceImpl implements CommunicationService {

    private final PrintStream systemOut;
    private final BufferedReader reader;
    private final MessageSource messageSource;

    public CommunicationConsoleServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream systemOut,
                                           @Value("#{T(java.lang.System).in}") InputStream systemIn,
                                           MessageSource messageSource) {
        this.systemOut = systemOut;
        this.reader = new BufferedReader(new InputStreamReader(systemIn));
        this.messageSource = messageSource;
    }

    @Override
    public void ask(String question) {
        systemOut.println(question);
    }

    @Override
    public String getAnswer() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            String exception = messageSource.getMessage("exception.communication-console.message",
                    new String[]{e.getMessage()}, LocaleContextHolder.getLocale());
            throw new CommunicationConsoleException(exception);
        }
    }
}
