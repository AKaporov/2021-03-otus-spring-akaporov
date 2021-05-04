package spring.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.parse.ParseInputStreamImpl;
import ru.otus.spring.parse.exception.ParseException;
import spring.generator.CreateArrayStringListGenerator;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс ParserInputStreamImpl")
class ParseInputStreamImplTest {
    private ParseInputStreamImpl parserInputStream;

    @BeforeEach
    void setUp() {
        parserInputStream = new ParseInputStreamImpl();
    }

    @Test
    @DisplayName("должен генерить исключение, если переданный поток null")
    void parserParserExceptionWhenStreamIsNull() {
        Exception exception = assertThrows(ParseException.class, () -> {
            parserInputStream.parser(null);
        });

        assertEquals(exception.getClass(), ParseException.class);
    }

    @Test
    @DisplayName("должен успешно парсить входящий поток")
    void shouldReadFromStream() {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("QuestionTest.csv");

        List<String[]> parsingResult = parserInputStream.parser(stream);

        List<String[]> expected = CreateArrayStringListGenerator.createOneQuestionInArray();

        assertThat(parsingResult).containsExactlyInAnyOrderElementsOf(expected);
    }
}