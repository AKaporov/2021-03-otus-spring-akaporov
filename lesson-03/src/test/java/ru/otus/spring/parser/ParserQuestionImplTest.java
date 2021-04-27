package ru.otus.spring.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;
import ru.otus.spring.generator.CreateArrayStringListGenerator;
import ru.otus.spring.generator.CreateQuestionListGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс ParserQuestionImpl")
class ParserQuestionImplTest {
    private ParserQuestionImpl parserQuestion;

    @Test
    @DisplayName("должен возвращать список вопросов из списка массива")
    void shouldListQuestionFromListArray() {
        parserQuestion = new ParserQuestionImpl();

        List<String[]> arrayList = CreateArrayStringListGenerator.createOneQuestionInArray();

        List<Question> parser = parserQuestion.parser(arrayList);

        List<Question> expected = CreateQuestionListGenerator.createOneQuestionInList();

        assertAll(() -> {
            assertEquals(expected.size(), parser.size());
            assertThat(expected.get(0).getAnswer()).isEqualTo(parser.get(0).getAnswer());
            assertThat(expected.get(0).getQuestion()).isEqualTo(parser.get(0).getQuestion());
            assertThat(expected.get(0).getRightAnswer()).isEqualTo(parser.get(0).getRightAnswer());
        });
    }
}