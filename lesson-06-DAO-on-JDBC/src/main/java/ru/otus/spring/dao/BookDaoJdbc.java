package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.exception.DaoException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book insertAll(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());

        KeyHolder kh = new GeneratedKeyHolder();

        jdbc.update("insert into books(title, author_id, genre_id) values(:title, :author_id, :genre_id)", params, kh);
        book.setId(kh.getKey().longValue());

        return book;
    }

    @Override
    public Book getAllById(long id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        List<Book> list = jdbc.query("select b.id as book_id " +
                "     , b.title      as book_title " +
                "     , a.id         as author_id " +
                "     , a.name       as author_name " +
                "     , g.id         as genre_id " +
                "     , g.name       as genre_name " +
                " from books        b " +
                "inner join authors a " +
                "        on a.id = b.author_id " +
                "inner join genres  g " +
                "        on g.id = b.genre_id " +
                "where b.id = :id", params, new BookMapper());

        return list.stream().findFirst().orElseThrow(() -> new DaoException(String.format("The book {%d} was not found. Check the request details.", id)));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        List<Book> list = jdbc.query("select b.id as book_id " +
                "    , b.title      as book_title " +
                "    , 0            as author_id " +
                "    , \'\'         as author_name " +
                "    , 0            as genre_id " +
                "    , \'\'         as genre_name " +
                " from books b " +
                "where b.id = :id", params, new BookMapper());

        return list.stream().findFirst().orElseThrow(() -> new DaoException(String.format("The book {%d} was not found. Check the request details.", id)));
    }

    @Override
    public Optional<Book> getByTitle(String titleBook) {
        MapSqlParameterSource params = new MapSqlParameterSource("titleBook", titleBook);
        List<Book> list = jdbc.query("select b.id as book_id " +
                "    , b.title      as book_title " +
                "    , 0            as author_id " +
                "    , \'\'         as author_name " +
                "    , 0            as genre_id " +
                "    , \'\'         as genre_name " +
                " from books b " +
                "where b.title = :titleBook", params, new BookMapper());

        return list.stream().findFirst();
    }

    @Override
    public Optional<Book> getAllByTitle(String titleBook) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("titleBook", titleBook);

        List<Book> list = jdbc.query("select b.id as book_id " +
                "    , b.title      as book_title " +
                "    , a.id         as author_id " +
                "    , a.name       as author_name " +
                "    , g.id         as genre_id " +
                "    , g.name       as genre_name " +
                " from books        b " +
                "inner join authors a " +
                "        on a.id = b.author_id " +
                "inner join genres  g " +
                "        on g.id = b.genre_id " +
                "where b.title = :titleBook", params, new BookMapper());

        return list.stream().findFirst();
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id as book_id " +
                "    , b.title      as book_title " +
                "    , a.id         as author_id " +
                "    , a.name       as author_name " +
                "    , g.id         as genre_id " +
                "    , g.name       as genre_name " +
                " from books        b " +
                "inner join authors a " +
                "        on a.id = b.author_id " +
                "inner join genres  g " +
                "        on g.id = b.genre_id " +
                "where 1 = 1", new BookMapper());
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", book.getId());

        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());

        jdbc.update("update books set title = :title where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long bookId = rs.getLong("book_id");
            String bookTitle = rs.getString("book_title");

            long authorId = rs.getLong("author_id");
            String authorName = rs.getString("author_name");

            long genreId = rs.getLong("genre_id");
            String genreName = rs.getString("genre_name");

            return new Book(bookId, bookTitle, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
    }
}
