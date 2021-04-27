package spring.generator;

import ru.otus.spring.domain.User;

public class UserGenerator {
    public static User createUser(String name, String surname){
        return new User(name, surname);
    }
}
