package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.generate.BookGenerator;
import ru.otus.spring.service.exception.BookNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Сервис по работе с Книгами")
class BookServiceImplTest {
    private static final String GOLDEN_KEY_BOOK_TITlE = "The Golden Key, or the Adventures of Pinocchio";
    private static final String GOLDEN_KEY_REVIEW_NEW = "Beautiful pictures";
    private static final int EXISTS_BOOK_COUNT = 4;
    @Autowired
    private BookServiceImpl bookService;

    @Test
    @DisplayName("должен находить конкретную книгу по её названию")
    void shouldFindBookByTitle() {
        BookDto expectedBook = BookGenerator.getBookDtoGoldenKey();

        BookDto actualBook = bookService.findBookByTitle(expectedBook.getTitle());

        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("должен генерировать исключение при поиске несуществующей книги")
    void shouldThrowExceptionNotExistsBook() {
        Book notExistsBook = BookGenerator.getNotExistsBook();

        assertThatThrownBy(() -> bookService.findBookByTitle(notExistsBook.getTitle())).isInstanceOf(BookNotFoundException.class);
    }

    @Test
    @DisplayName("должен находить все книги библиотеки")
    void shouldFindAllBooks() {
        List<BookDto> actualBooks = bookService.findAllBooks();

        assertThat(actualBooks).isNotNull().isNotEmpty().hasSize(EXISTS_BOOK_COUNT)
                .matches(b -> !"".equalsIgnoreCase(b.get(0).getTitle()))
                .matches(b -> !"".equalsIgnoreCase(b.get(0).getAuthor()))
                .matches(b -> !"".equalsIgnoreCase(b.get(0).getGenre()));
    }

    @Test
    @DisplayName("должен находить все отзывы о книге по её названию")
    void shouldFindBookReviewsByBookTitle() {
        BookDto bookGoldenKey = BookGenerator.getBookDtoGoldenKey();
        BookReviewDto exceptedBookReviews = BookGenerator.getReviewsByGoldenKey();

        BookReviewDto actualReviews = bookService.findBookReviewsByTitle(bookGoldenKey.getTitle());

        assertThat(actualReviews).isNotNull().usingRecursiveComparison().isEqualTo(exceptedBookReviews);
    }

    @Test
    @Transactional
    @DisplayName("должен сохранять новую книгу, без отзывов")
    void shouldSaveNewBook() {
        BookDto expectedBook = BookGenerator.getNewBookDtoToSave();

        BookDto actualBook = bookService.save(expectedBook);

        assertThat(actualBook).isNotNull()
                .matches(b -> b.getTitle().equalsIgnoreCase(expectedBook.getTitle()))
                .matches(b -> b.getAuthor().equalsIgnoreCase(expectedBook.getAuthor()))
                .matches(b -> b.getGenre().equalsIgnoreCase(expectedBook.getGenre()));
    }

    @Test
    @Transactional
    @DisplayName("должен удалять книгу")
    void shouldRemoveBook() {
        BookDto bookDtoToDelete = BookGenerator.getBookDtoGoldenKey();

        assertThatCode(() -> bookService.findBookByTitle(bookDtoToDelete.getTitle())).doesNotThrowAnyException();

        bookService.remove(bookDtoToDelete);

        assertThatThrownBy(() -> bookService.findBookByTitle(bookDtoToDelete.getTitle())).isInstanceOf(BookNotFoundException.class);
    }

    @Transactional
    @Test
    @DisplayName("должен добавлять отзыв о книге")
    void shouldAddReviewToBook() {
        bookService.saveReview(GOLDEN_KEY_BOOK_TITlE, GOLDEN_KEY_REVIEW_NEW);

        BookReviewDto actualBookReviews = bookService.findBookReviewsByTitle(GOLDEN_KEY_BOOK_TITlE);

        assertThat(actualBookReviews.getReviews()).isNotNull().isNotEmpty().hasSize(4);
    }
}