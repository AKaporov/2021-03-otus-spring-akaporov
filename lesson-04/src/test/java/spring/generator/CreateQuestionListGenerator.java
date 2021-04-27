package spring.generator;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionListGenerator {
    public static List<Question> createOneQuestionInList(){
        Question question = new Question("B",
                "Where, as they say, a person who is in the vanguard of something settled?",
                getOneAnswerList());

        List<Question> result = new ArrayList<>(1);
        result.add(question);

        return result;
    }

    private static List<String> getOneAnswerList() {
        List<String> result = new ArrayList<>(4);

        result.add(" A - on the seventh sky");
        result.add(" B - in the foreground");
        result.add(" C - on foot");
        result.add(" D - in the ninth month");

        return result;
    }
}
