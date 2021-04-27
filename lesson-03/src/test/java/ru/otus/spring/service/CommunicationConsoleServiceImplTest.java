package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.service.exception.CommunicationConsoleException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс CommunicationConsoleService")
class CommunicationConsoleServiceImplTest {
    private CommunicationConsoleServiceImpl communicationConsoleService;
    private ByteArrayOutputStream outputStream;

    @Mock
    private InputStream systemIn;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        communicationConsoleService = new CommunicationConsoleServiceImpl(printStream, systemIn);
    }

    @Test
    @DisplayName("должен спрашивать \"How are you?\"")
    void ask() {
        communicationConsoleService.ask("How are you?");
        assertEquals(outputStream.toString(), "How are you?" + "\r\n");
    }

    @Test
    @DisplayName("должен генерить исключение, если ничего не ввели в консоле")
    void shouldCommunicationConsoleException() {
        Exception exception = assertThrows(CommunicationConsoleException.class, () -> {
            communicationConsoleService.getAnswer();
        });

        assertEquals(exception.getClass(), CommunicationConsoleException.class);
    }
}