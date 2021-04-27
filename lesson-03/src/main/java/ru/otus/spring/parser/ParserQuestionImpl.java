package ru.otus.spring.parser;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для преобразования массива строк в список вопросов.
 */

@Component
public class ParserQuestionImpl implements ParserQuestion {
    @Override
    public List<Question> parser(List<String[]> arrayList) {
        List<Question> result = new LinkedList<>();

        for (String[] array : arrayList) {

            List<String> answerList = getFourAnswerFromArray(array);

            result.add(new Question(array[0], array[1], answerList));
        }

        return result;
    }

    private List<String> getFourAnswerFromArray(String[] array) {
        List<String> result = new ArrayList<>(4);

        result.add(array[2]);
        result.add(array[3]);
        result.add(array[4]);
        result.add(array[5]);

        return result;
    }
}
