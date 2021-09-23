package ru.otus.spring.healthindicator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class FillPercentageHealthIndicator implements HealthIndicator {
    private static final Logger LOGGER = Logger.getLogger(FillPercentageHealthIndicator.class.getName());
    private final LibraryIndicator libraryIndicator;

    @Value("${library.healthindicator.maximum-number-books}")
    private int maxNumberBooks;

    @Value("${library.healthindicator.fill-percentage}")
    private float fillPercentage;

    @Override
    public Health health() {
        Health.Builder status = Health.up();
        float currentFillPercentage = getCurrentFillPercentage();
        if (currentFillPercentage >= fillPercentage) {
            status = Health.down();
            String message = "The library is filled with books on " + currentFillPercentage + "%!";
            status.withDetail("WARN", message);

            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info(new StringBuilder(message).toString());
            }
        }

        return status.build();
    }

    private float getCurrentFillPercentage() {
        int numberBooksInLibrary = libraryIndicator.getNumberBooksInLibrary();

        if (maxNumberBooks == 0) {
            return -1;
        }

        return (float) (numberBooksInLibrary * 100 / maxNumberBooks);
    }
}
