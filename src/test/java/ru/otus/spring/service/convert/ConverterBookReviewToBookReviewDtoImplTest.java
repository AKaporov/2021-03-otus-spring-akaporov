package ru.otus.spring.service.convert;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.generate.BookGenerator;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис по конвертациии отзывов на книгу")
class ConverterBookReviewToBookReviewDtoImplTest {

    private ConverterBookReviewToBookBookReviewImplBook convert;

    @Test
    @DisplayName("должен конвертировать переданные отзывы в корректный объект для добавления в БД")
    void shouldConvertBookReviewDtoToReviewList() {
        convert = new ConverterBookReviewToBookBookReviewImplBook();

        BookReviewDto expectedBookReviews = BookGenerator.getReviewsByGoldenKey();
        Book bookGoldenKey = BookGenerator.getExistsBookGoldenKey();

        BookReviewDto actualReviews = convert.convertToList(bookGoldenKey);

        assertThat(actualReviews).isNotNull().usingRecursiveComparison().isEqualTo(expectedBookReviews);
    }
}