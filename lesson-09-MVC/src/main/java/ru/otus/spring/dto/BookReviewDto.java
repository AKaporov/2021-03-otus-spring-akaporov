package ru.otus.spring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BookReviewDto {
    private final long bookId;
    private final String bookTitle;
    private final List<String> reviews;

    @Override
    public String toString() {
        return "id = " + this.getBookId() + ", bookTitle = " + this.getBookTitle()
                + (reviews.isEmpty() ? "" : "; " + String.join(", ", reviews));
    }
}
