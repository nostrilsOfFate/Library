package lena.library.dao;

import lena.library.model.Author;
import lena.library.model.Book;
import lena.library.model.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert(Book book) {
        if (book.isNew()) {//"insert into books (name, description, author, genre) values (?, ?, ?, ?) ",
          //  book.getName(), book.getDescription(), book.getAuthor().getName(),
                  //  book.getGenre().getName()
            return namedParameterJdbcOperations.getJdbcOperations().
                    update("insert into data_genre.book (name,writtenYear,description) values(?,?,?)", book.getName(), book.getWrittenYear(),book.getDescription());
        } else {
            return namedParameterJdbcOperations.getJdbcOperations().update("update books set name=?,writtenYear=?, description=? where id=?",
                    book.getName(),book.getWrittenYear(), book.getDescription(), book.getId());
        }
    }

    @Override
    public Book getById(int id) throws DataAccessException {
        Map<String, Integer> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select * from data_genre.book where id = :id", params, new BookDaoImpl.BookMapper());
    }

    @Override
    public List<Book> getByName(String name) throws DataAccessException {
        Map<String, String> params = Collections.singletonMap("name", name);
        //queryForObject("select * from data_genre.book where name = :name", params, new BookDaoImpl.BookMapper());
        return (List<Book>) namedParameterJdbcOperations.queryForList("select * from data_genre.book where name = :name",
               params, Book.class);

    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select * from data_genre.book", new BookDaoImpl.BookMapper());
    }

    @Override
    public int deleteById(int id) {
        Map<String, Integer> params = Collections.singletonMap("name", id);
        return namedParameterJdbcOperations.update("delete * from data_genre.book where name = :name", params);
    }

    @Override
    public int deleteByName(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        //update("DELETE * from data_genre.book where name = :name", params);
        return namedParameterJdbcOperations.update("DELETE * from data_genre.book where name = :name", params);

    }
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int writtenYear = resultSet.getInt("writtenYear");
            String description = resultSet.getString("description");
            Set<Author> authors = new HashSet<Author>(); // ?
            Set<Genre> genres = new HashSet<Genre>();
            //таки шо делать с авторами и жанрами в списках, списки то пустые!
          //  Genre genre = new Genre(null, resultSet.getString("genre"));
            return new Book(id, name, writtenYear,description, authors, genres);
        }
    }
}
