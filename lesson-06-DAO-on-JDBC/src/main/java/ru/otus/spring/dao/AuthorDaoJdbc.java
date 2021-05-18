package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
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
    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("surName", author.getSurName());
        params.addValue("name", author.getName());
        params.addValue("patronymic", author.getPatronymic());

        KeyHolder kh = new GeneratedKeyHolder();

        jdbc.update("insert into authors(`surname`, `name`, `patronymic`) values(:surName, :name, :patronymic)", params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public Optional<Author> getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<Author> list = jdbc.query("select * from authors where id = :id", params, new AuthorMapper());
        return list.stream().findFirst();
    }


    @Override
    public Optional<Author> getByFullName(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("surName", author.getSurName());
        params.addValue("name", author.getName());
        params.addValue("patronymic", author.getPatronymic());

        List<Author> list = jdbc.query("select * from authors where `surName` = :surName and `name` = :name and `patronymic` = :patronymic",
                params, new AuthorMapper());
        return list.stream().findFirst();

    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void update(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", author.getId());
        params.addValue("surName", author.getSurName());
        params.addValue("name", author.getName());
        params.addValue("patronymic", author.getPatronymic());

        jdbc.update("update authors set `surName` = :surName, `name` = :name, `patronymic` = :patronymic where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update("delete from authors where id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"),
                    rs.getString("surName"), rs.getString("name"), rs.getString("patronymic"));
        }
    }
}
