package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.exception.BookNotFoundException;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/api/v1/books", params = "BookTitle")
    public BookDto getBookByTitleInRequestParams(
            @RequestParam(value = "BookTitle", required = true, defaultValue = "")
                    String bookTitle) {
        return bookService.findBookByTitle(bookTitle);
    }

    @GetMapping(value = "/api/v1/books")
    public List<BookDto> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/api/v1/books/{BookTitle}/reviews")
    public BookReviewDto getBookReviewsByBookTitleInPath(
            @PathVariable(value = "BookTitle", required = true)
                    String bookTitle) {
        return bookService.findBookReviewsByTitle(bookTitle);
    }

    @PostMapping(value = "/api/v1/books")
    public BookDto createNewBookInRequestBody(
            @RequestBody(required = true)
                    BookDto dto) {
        return bookService.save(dto);
    }

    @DeleteMapping(value = "/api/v1/books/{BookTitle}")
    public void deleteBookByBookTitleInPath(
            @PathVariable(value = "BookTitle", required = true)
                    String bookTitle) {
        BookDto foundBook = bookService.findBookByTitle(bookTitle);
        bookService.remove(new BookDto(foundBook.getTitle(), foundBook.getAuthor(), foundBook.getGenre()));
    }

    @PostMapping(value = "/api/v1/books/{BookTitle}/review")
    public void saveReviewInRequestParams(
            @PathVariable(value = "BookTitle", required = true)
                    String bookTitle,
            @RequestParam(value = "review", required = true, defaultValue = "")
                    String review) {
        bookService.saveReview(bookTitle, review);
    }

    @ExceptionHandler(BookNotFoundException.class)
    private ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
        return ResponseEntity.badRequest().body(String.format("The book {%s} was not found. Check the request details.", e.getErrorMessage()));
    }
}
