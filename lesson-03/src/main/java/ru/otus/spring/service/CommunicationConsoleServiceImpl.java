package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
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

    public CommunicationConsoleServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream systemOut,
                                           @Value("#{T(java.lang.System).in}") InputStream systemIn) {
        this.systemOut = systemOut;
        this.reader = new BufferedReader(new InputStreamReader(systemIn));
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
            String exception = new StringBuilder("Error read answer from console! ")
                    .append(e.getMessage())
                    .toString();
            throw new CommunicationConsoleException(exception);
        }
    }
}
