package spring.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;
import ru.otus.spring.parse.ParseQuestionImpl;
import spring.generator.CreateArrayStringListGenerator;
import spring.generator.CreateQuestionListGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс ParserQuestionImpl")
class ParseQuestionImplTest {
    private ParseQuestionImpl parserQuestion;

    @Test
    @DisplayName("должен возвращать список вопросов из списка массива")
    void shouldListQuestionFromListArray() {
        parserQuestion = new ParseQuestionImpl();

        List<String[]> arrayList = CreateArrayStringListGenerator.createOneQuestionInArray();

        List<Question> parsingResult = parserQuestion.parser(arrayList);

        List<Question> expected = CreateQuestionListGenerator.createOneQuestionInList();

        assertThat(parsingResult).usingFieldByFieldElementComparator().containsExactlyInAnyOrderElementsOf(expected);
    }
}