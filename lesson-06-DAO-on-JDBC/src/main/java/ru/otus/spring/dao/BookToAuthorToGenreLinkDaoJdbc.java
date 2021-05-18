package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookToAuthorToGenreLink;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookToAuthorToGenreLinkDaoJdbc implements BookToAuthorToGenreLinkDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Optional<BookToAuthorToGenreLink> getByBookTitle(String bookTitle) {
        MapSqlParameterSource params = new MapSqlParameterSource("bookTitle", bookTitle);

        List<BookToAuthorToGenreLink> list = jdbc.query("select t.id" +
                "     , b.id         as book_id" +
                "     , b.title      as book_title" +
                "     , a.id         as author_id" +
                "     , a.surname    as author_surName" +
                "     , a.name       as author_name" +
                "     , a.patronymic as author_patronymic" +
                "     , g.id         as genre_id" +
                "     , g.name       as genre_name" +
                " from books   b " +
                "inner join bookstoauthorstogenreslink t " +
                "        on t.book_id = b.id " +
                "inner join authors a " +
                "         on a.id = t.author_id " +
                "inner join genres  g " +
                "        on g.id = t.genre_id " +
                "where b.title = :bookTitle", params, new BookToAuthorToGenreLinkMapper());

        return list.stream().findFirst();
    }

    @Override
    public long insert(BookToAuthorToGenreLink link) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("book_id", link.getBook().getId());
        params.addValue("author_id", link.getAuthor().getId());
        params.addValue("genre_id", link.getGenre().getId());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into bookstoauthorstogenreslink(`book_id`, `author_id`, `genre_id`) values(:book_id, :author_id, :genre_id)", params, kh);

        return kh.getKey().longValue();
    }

    @Override
    public Optional<BookToAuthorToGenreLink> getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        List<BookToAuthorToGenreLink> list = jdbc.query("select t.id" +
                "     , b.id         as book_id" +
                "     , b.title      as book_title" +
                "     , a.id         as author_id" +
                "     , a.surname    as author_surName" +
                "     , a.name       as author_name" +
                "     , a.patronymic as author_patronymic" +
                "     , g.id         as genre_id" +
                "     , g.name       as genre_name" +
                " from bookstoauthorstogenreslink t " +
                "inner join books   b " +
                "        on b.id = t.book_id " +
                "inner join authors a " +
                "         on a.id = t.author_id " +
                "inner join genres  g " +
                "        on g.id = t.genre_id " +
                "where t.id = :id", params, new BookToAuthorToGenreLinkMapper());

        return list.stream().findFirst();
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update("delete from bookstoauthorstogenreslink where id = :id", params);
    }

    @Override
    public List<BookToAuthorToGenreLink> getAllLink() {
        return jdbc.query("select t.id" +
                "     , b.id         as book_id" +
                "     , b.title      as book_title" +
                "     , a.id         as author_id" +
                "     , a.surname    as author_surName" +
                "     , a.name       as author_name" +
                "     , a.patronymic as author_patronymic" +
                "     , g.id         as genre_id" +
                "     , g.name       as genre_name" +
                " from bookstoauthorstogenreslink t " +
                "inner join books   b " +
                "        on b.id = t.book_id " +
                "inner join authors a " +
                "         on a.id = t.author_id " +
                "inner join genres  g " +
                "        on g.id = t.genre_id " +
                "where 1 = 1", new BookToAuthorToGenreLinkMapper());
    }

    private static class BookToAuthorToGenreLinkMapper implements RowMapper<BookToAuthorToGenreLink> {
        @Override
        public BookToAuthorToGenreLink mapRow(ResultSet rs, int rowNum) throws SQLException {
            long bookId = rs.getLong("book_id");
            String bookTitle = rs.getString("book_title");

            long authorId = rs.getLong("author_id");
            String authorSurName = rs.getString("author_surName");
            String authorName = rs.getString("author_name");
            String authorPatronymic = rs.getString("author_patronymic");

            long genreId = rs.getLong("genre_id");
            String genreName = rs.getString("genre_name");

            return new BookToAuthorToGenreLink(rs.getLong("id"),
                    new Book(bookId, bookTitle),
                    new Author(authorId, authorSurName, authorName, authorPatronymic),
                    new Genre(genreId, genreName));
        }
    }
}
