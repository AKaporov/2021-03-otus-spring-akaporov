package ru.otus.spring.service.convert;

import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookReviewDto;

public interface ConvertBookReviewToBookReviewDto {
    BookReviewDto convertToList(Book book);
}
