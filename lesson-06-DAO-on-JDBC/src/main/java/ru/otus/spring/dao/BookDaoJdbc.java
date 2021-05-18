package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

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
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource("title", book.getTitle());
        KeyHolder kh = new GeneratedKeyHolder();

        jdbc.update("insert into books(`title`) values(:title)", params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        List<Book> list = jdbc.query("select * from books where id = :id", params, new BookMapper());
        return list.stream().findFirst();
    }

    @Override
    public Optional<Book> getByTitle(String titleBook) {
        MapSqlParameterSource params = new MapSqlParameterSource("bookTitle", titleBook);
        List<Book> list = jdbc.query("select * from books where title = :bookTitle", params, new BookMapper());
        return list.stream().findFirst();
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", book.getId());

        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());

        jdbc.update("update books set `title` = :title where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getLong("id"), rs.getString("title"));
        }
    }
}
