package ru.otus.spring.service.convert;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.BookDto;

import java.util.ArrayList;

@Service
public class ConverterBookDtoToBookImpl implements ConverterTo<BookDto, Book> {

    private static final long GENRE_EMPTY_ID = 0L;
    private static final long EMPTY_AUTHOR_ID = 0L;
    private static final long EMPTY_BOOK_ID = 0L;

    @Override
    public Book convert(BookDto obj) {
        return new Book(EMPTY_BOOK_ID, obj.getTitle(),
                new Author(EMPTY_AUTHOR_ID, obj.getAuthor()),
                new Genre(GENRE_EMPTY_ID, obj.getGenre()),
                new ArrayList<>()
        );
    }
}
