package ru.otus.spring.service.convert;

import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookReviewDto;

import java.util.ArrayList;
import java.util.List;

public class ConverterBookReviewToBookBookReviewImplBook implements ConverterBookReviewToBookReviewDto {
    @Override
    public BookReviewDto convertToList(Book book) {
        List<String> reviews = new ArrayList<>(book.getReviews().size());
        book.getReviews().stream().forEach(
                r -> reviews.add(r.getText())
        );
        return new BookReviewDto(book.getId(), book.getTitle(), reviews);
    }
}
