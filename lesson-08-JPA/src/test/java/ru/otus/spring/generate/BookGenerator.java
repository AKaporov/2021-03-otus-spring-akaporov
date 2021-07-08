package ru.otus.spring.generate;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.domain.Review;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;

import java.util.ArrayList;
import java.util.List;

public class BookGenerator {

    private static final long GOLDEN_KEY_BOOK_ID = 1;
    private static final String GOLDEN_KEY_BOOK_TITlE = "The Golden Key, or the Adventures of Pinocchio";
    private static final String GOLDEN_KEY_BOOK_REVIEW_ONE = "The book is informative, the child really liked it";
    private static final String GOLDEN_KEY_BOOK_REVIEW_TWO = "I recommend to first graders for reading!";
    private static final String GOLDEN_KEY_BOOK_REVIEW_THREE = "Format, convenient to take with you. Color illustrations available, hardcover";
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy A. N.";
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Story";
    private static final long BOOK_NOT_EXISTS_ID = 10000;
    private static final String BOOK_NOT_EXISTS_NAME = "Street Book";
    private static final String BOOK_NEW_NAME_ASSISTANT = "Assistant";
    private static final long BOOK_EMPTY_ID = 0;
    private static final long AUTHOR_EMPTY_ID = 0;
    private static final long GENRE_EMPTY_ID = 0;
    private static final Long REVIEW_EMPTY_ID = null;
    private static final String AUTHOR_NEW_NAME_MARKOV = "Markov A. K.";
    private static final String GENRE_BOOK_FOR_CHILDREN = "Book for children";
    private static final String REVIEW_ASSISTANT_ONE = "Beautiful pictures";
    private static final String REVIEW_ASSISTANT_TWO = "Book for children";
    private static final long EXISTS_REVIEW_ID_ONE = 1L;
    private static final long EXISTS_REVIEW_ID_TWO = 2L;
    private static final long EXISTS_REVIEW_ID_TREE = 3L;

    public static BookReviewDto getReviewsByGoldenKey() {
        List<String> reviews = new ArrayList<>(3);
        reviews.add(GOLDEN_KEY_BOOK_REVIEW_ONE);
        reviews.add(GOLDEN_KEY_BOOK_REVIEW_TWO);
        reviews.add(GOLDEN_KEY_BOOK_REVIEW_THREE);

        return new BookReviewDto(GOLDEN_KEY_BOOK_ID, GOLDEN_KEY_BOOK_TITlE, reviews);
    }

    public static Book getNotExistsBook() {
        Author authorNotExists = new Author();
        Genre genreNotExists = new Genre();
        List<Review> reviewsNotExists = new ArrayList<>();
        return new Book(BOOK_NOT_EXISTS_ID, BOOK_NOT_EXISTS_NAME, authorNotExists, genreNotExists, reviewsNotExists);
    }

    public static BookDto getNewBookDtoToSave() {
        return new BookDto(BOOK_NEW_NAME_ASSISTANT, AUTHOR_NEW_NAME_MARKOV, GENRE_BOOK_FOR_CHILDREN);
    }

    public static BookDto getBookDtoGoldenKey() {
        return new BookDto(GOLDEN_KEY_BOOK_TITlE, EXISTING_AUTHOR_NAME, EXISTING_GENRE_NAME);
    }

    public static Book getExistsBookGoldenKey() {
        Author author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Genre genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Review> reviews = new ArrayList<>(3);
        reviews.add(new Review(EXISTS_REVIEW_ID_ONE, GOLDEN_KEY_BOOK_REVIEW_ONE));
        reviews.add(new Review(EXISTS_REVIEW_ID_TWO, GOLDEN_KEY_BOOK_REVIEW_TWO));
        reviews.add(new Review(EXISTS_REVIEW_ID_TREE, GOLDEN_KEY_BOOK_REVIEW_THREE));
        return new Book(GOLDEN_KEY_BOOK_ID, GOLDEN_KEY_BOOK_TITlE, author, genre, reviews);
    }
}
