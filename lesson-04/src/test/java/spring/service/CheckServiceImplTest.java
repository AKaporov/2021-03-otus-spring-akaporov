package spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.service.CheckServiceImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс CheckServiceImpl")
class CheckServiceImplTest {
    private CheckServiceImpl checkService;

    @BeforeEach
    void setUp() {
        checkService = new CheckServiceImpl();
    }

    @Test
    @DisplayName("должен возвращать true, если ответ верный")
    void shouldReturnTrue() {
        boolean check = checkService.check("B", "B");

        assertTrue(check);
    }

    @Test
    @DisplayName("должен возвращать false, если ответ не верный")
    void shouldReturnFalse() {
        boolean check = checkService.check("B", "Q");

        assertFalse(check);
    }
}