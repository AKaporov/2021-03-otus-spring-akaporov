package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс LibraryLocaleServiceImpl")
class LocaleServiceImplTest {

    private LocaleServiceImpl libraryLocaleService;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        libraryLocaleService = new LocaleServiceImpl(messageSource);
    }

    @Test
    @DisplayName("должен корректно локализовывать сообщение")
    void shouldCorrectLocale() {
        String expectedMessage = "Welcome to the library!";
        when(messageSource.getMessage("shell.welcome.test", null, LocaleContextHolder.getLocale())).thenReturn(expectedMessage);

        String actualMessage = libraryLocaleService.getMessage("shell.welcome.test", null);

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}