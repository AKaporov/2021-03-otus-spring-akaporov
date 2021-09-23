package ru.otus.spring.healthindicator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.BookService;

@Service
@RequiredArgsConstructor
public class LibraryIndicatorImpl implements LibraryIndicator {
    private final BookService bookService;

    @Override
    public int getNumberBooksInLibrary() {
        return bookService.getNumberBooksInLibrary();
    }
}
