package spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.CheckService;
import ru.otus.spring.service.CheckServiceImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("Класс CheckServiceImpl")
class CheckServiceImplTest {
    @Autowired
    private CheckService checkService;

    @Configuration
    static class TestConfiguration {

        @Bean
        public CheckService checkService() {
            return new CheckServiceImpl();
        }
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