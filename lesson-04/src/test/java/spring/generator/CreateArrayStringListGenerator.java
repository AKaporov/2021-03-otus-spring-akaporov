package spring.generator;

import java.util.ArrayList;
import java.util.List;

public class CreateArrayStringListGenerator {
    public static List<String[]> createOneQuestionInArray(){
        List<String[]> result = new ArrayList<>(1);
        String[] strings = {"B",
                "Where, as they say, a person who is in the vanguard of something settled?",
                " A - on the seventh sky",
                " B - in the foreground",
                " C - on foot",
                " D - in the ninth month"
        };
        result.add(strings);
        return result;
    }
}
