package ru.otus.spring.service.convert;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;

@Service
public class ConvertBookToDtoImpl implements ConvertTo<Book, BookDto> {
    @Override
    public BookDto convert(Book obj) {
        return new BookDto(obj.getTitle(), obj.getAuthor().getName(), obj.getGenre().getName());
    }

}
