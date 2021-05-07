package spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.AnswerService;
import ru.otus.spring.service.AnswerServiceImpl;
import ru.otus.spring.service.CommunicationConsoleServiceImpl;
import ru.otus.spring.service.CommunicationService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Класс AnswerServiceImpl")
class AnswerServiceImplTest {
    public static final String MIKE_NAME = "Mike";

    @Autowired
    private AnswerService answerService;
    @MockBean
    private CommunicationService communicationService;

    private static final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Configuration
    static class TestConfiguration {

        @Bean
        public AnswerService answerService() {
            PrintStream systemOut = new PrintStream(out);
            String testInput = MIKE_NAME + "\r\n";
            InputStream systemIn = new ByteArrayInputStream(testInput.getBytes());

            return new AnswerServiceImpl(new CommunicationConsoleServiceImpl(systemOut, systemIn));
        }
    }

    @Test
    @DisplayName("должен получать ответ")
    void getAnswer() {
        String expected = MIKE_NAME;
        when(communicationService.getAnswer()).thenReturn(expected);
        String answer = answerService.getAnswer();
        assertEquals(expected, answer);
    }
}