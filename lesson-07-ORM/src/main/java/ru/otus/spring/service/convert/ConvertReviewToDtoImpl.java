package ru.otus.spring.service.convert;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Review;
import ru.otus.spring.dto.BookReviewDto;

import java.util.stream.Collectors;

@Service
public class ConvertReviewToDtoImpl implements ConvertTo<Book, BookReviewDto> {
    @Override
    public BookReviewDto convert(Book obj) {
        return new BookReviewDto(obj.getId(), obj.getTitle(),
                obj.getReviews().stream().map(Review::getText).collect(Collectors.toList())
        );
    }
}
