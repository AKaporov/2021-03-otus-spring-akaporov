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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into authors(name) values(:name)", params, kh);
        author.setId(kh.getKey().longValue());

        return author;
    }

    @Override
    public Author getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<Author> list = jdbc.query("select id, name from authors where id = :id", params, new AuthorMapper());
        return list.stream().findFirst().orElseThrow(() -> new DaoException(String.format("The author {%d} was not found. Check the request details.", id)));
    }

    @Override
    public Optional<Author> getByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);

        List<Author> list = jdbc.query("select id, name from authors where name = :name", params, new AuthorMapper());
        return list.stream().findFirst();
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name from authors", new AuthorMapper());
    }

    @Override
    public void update(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", author.getId());
        params.addValue("name", author.getName());

        jdbc.update("update authors set name = :name where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update("delete from authors where id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"));
        }
    }
}
