package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.exception.DaoException;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource("name", genre.getName());
        KeyHolder kh = new GeneratedKeyHolder();

        jdbc.update("insert into genres(name) values(:name)", params, kh);
        genre.setId(kh.getKey().longValue());

        return genre;
    }

    @Override
    public Genre getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        List<Genre> list = jdbc.query("select id, name from genres where id = :id", params, new GenreMapper());
        return list.stream().findFirst().orElseThrow(() -> new DaoException(String.format("The genre {%d} was not found. Check the request details.", id)));
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", new GenreMapper());
    }

    @Override
    public void update(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", genre.getId());
        params.addValue("name", genre.getName());

        jdbc.update("update genres set `name` = :name where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);

        jdbc.update("delete from genres where id = :id", params);
    }


    @Override
    public Optional<Genre> getByName(String genreName) {
        MapSqlParameterSource params = new MapSqlParameterSource("genreName", genreName);
        List<Genre> list = jdbc.query("select id, name from genres where name = :genreName", params, new GenreMapper());
        return list.stream().findFirst();
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }
}
