package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс UserServiceImpl")
class UserServiceImplTest {
    private UserServiceImpl userService;

    @Mock
    private CommunicationService communicationService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(communicationService);
    }

    @Test
    @DisplayName("должен возвращать Фамилию и Имя проверяемого пользователя")
    void getUser() {
        Mockito.when(communicationService.getAnswer()).thenReturn("name");

        User user = userService.getUser();

        assertAll(() -> {
            assertThat(user.getName()).isEqualTo("name");
            assertThat(user.getSurname()).isEqualTo("name");
        });
    }
}