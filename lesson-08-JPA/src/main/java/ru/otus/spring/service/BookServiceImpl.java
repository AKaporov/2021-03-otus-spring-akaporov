package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Review;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookReviewDto;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.service.convert.ConverterBookDtoToBookImpl;
import ru.otus.spring.service.convert.ConverterBookToDtoImpl;
import ru.otus.spring.service.convert.ConverterReviewToDtoImpl;
import ru.otus.spring.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepositoryJpa bookRepository;
    private final ConverterBookToDtoImpl bookToDto;
    private final ConverterReviewToDtoImpl reviewToDto;
    private final ConverterBookDtoToBookImpl dtoToBook;

    @Transactional(readOnly = true)
    @Override
    public BookDto findBookByTitle(String title) {
        Book foundBook = findByTitle(title);
        return bookToDto.convert(foundBook);
    }

    private Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(
                () -> new ServiceException(String.format("The book {%s} was not found. Check the request details.", title))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAllBook() {
        List<Book> foundBooks = bookRepository.findAll();

        return foundBooks.stream()
                .map(bookToDto::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public BookReviewDto findBookReviewsByTitle(String bookTitle) {
        Book foundBook = findByTitle(bookTitle);
        return reviewToDto.convert(foundBook);
    }

    @Transactional
    @Override
    public Book save(BookDto bookDto) {
        Book book = dtoToBook.convert(bookDto);

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public void remove(BookDto bookDto) {
        Book foundBook = findByTitle(bookDto.getTitle());
        bookRepository.delete(foundBook);
    }

    @Transactional
    @Override
    public void saveReview(String bookTitle, String review) {
        Book foundBook = findByTitle(bookTitle);

        Review newReview = new Review(null, review);
        foundBook.getReviews().add(newReview);

        bookRepository.save(foundBook);
    }
}
