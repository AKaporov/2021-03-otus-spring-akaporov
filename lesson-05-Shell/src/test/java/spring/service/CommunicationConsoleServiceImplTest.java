package spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.CommunicationConsoleServiceImpl;
import ru.otus.spring.service.CommunicationService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@DisplayName("Класс CommunicationConsoleService")
class CommunicationConsoleServiceImplTest {
    private static final String HOW_ARE_YOU = "How are you?";

    @Autowired
    private CommunicationService communicationConsoleService;

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Configuration
    static class TestConfiguration {
        @Bean
        public CommunicationService communicationService() {
            PrintStream systemOut = new PrintStream(outputStream);
            String testInput = HOW_ARE_YOU + "\r\n";
            InputStream systemIn = new ByteArrayInputStream(testInput.getBytes());

            return new CommunicationConsoleServiceImpl(systemOut, systemIn);
        }
    }

    @Test
    @DisplayName("должен спрашивать \"How are you?\"")
    void shouldAskHowAreYou() {
        communicationConsoleService.ask(HOW_ARE_YOU);
        assertEquals(outputStream.toString(), "How are you?" + "\r\n");
    }

}