package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.CommunicationService;
import ru.otus.spring.service.UserServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс UserServiceImpl")
class UserServiceImplTest {
    private UserServiceImpl userService;

    @Mock
    private CommunicationService communicationService;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(communicationService, messageSource);
    }

    @Test
    @DisplayName("должен возвращать Фамилию и Имя проверяемого пользователя")
    void getUser() {
        User expectedUser = new User("name", "surname");
        Mockito.when(communicationService.getAnswer())
                .thenReturn(expectedUser.getName())
                .thenReturn(expectedUser.getSurname());

        User user = userService.getUser();

        assertThat(user).usingRecursiveComparison().isEqualTo(expectedUser);
    }
}