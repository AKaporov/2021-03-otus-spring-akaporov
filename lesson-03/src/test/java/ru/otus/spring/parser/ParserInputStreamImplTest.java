package ru.otus.spring.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.generator.CreateArrayStringListGenerator;
import ru.otus.spring.parser.exception.ParserException;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс ParserInputStreamImpl")
class ParserInputStreamImplTest {
    private ParserInputStreamImpl parserInputStream;

    @BeforeEach
    void setUp() {
        parserInputStream = new ParserInputStreamImpl();
    }

    @Test
    @DisplayName("должен генерить исключение, если переданный поток null")
    void parserParserExceptionWhenStreamIsNull() {
        Exception exception = assertThrows(ParserException.class, () -> {
            parserInputStream.parser(null);
        });

        assertEquals(exception.getClass(), ParserException.class);
    }

    @Test
    @DisplayName("должен успешно парсить входящий поток")
    void shouldReadFromStream() {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("QuestionTest.csv");

        List<String[]> parser = parserInputStream.parser(stream);

        List<String[]> expected = CreateArrayStringListGenerator.createOneQuestionInArray();
        assertAll(() -> {
            assertEquals(expected.size(), parser.size());
            assertThat(expected.get(0)).isEqualTo(parser.get(0));
        });
    }
}