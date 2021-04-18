package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс AnswerServiceImpl")
class AnswerServiceImplTest {

    private AnswerServiceImpl answerService;
    @Mock
    private CommunicationService communicationService;

    @BeforeEach
    void setUp() {
        answerService = new AnswerServiceImpl(communicationService);
    }

    @Test
    @DisplayName("должен получать ответ")
    void getAnswer() {
        String expected = "B";
        when(communicationService.getAnswer()).thenReturn(expected);
        String answer = answerService.getAnswer();
        assertEquals(expected, answer);
    }
}