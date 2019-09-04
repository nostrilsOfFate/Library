package lena.library.dao;

import lena.library.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Component
public class BookDaoImpl implements BookDao {


    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book insert(Book book) {

        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into library.books (name,description,writtenYear) values(?,?,?)",
                    new String[]{book.getName(), book.getDescription(), book.getWrittenYear().toString()});
            ps.setString(1, book.getName());
            ps.setString(2, book.getDescription());
            ps.setInt(3, book.getWrittenYear());
            return ps;
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        book.setId(holder.getKey().intValue());
        return book;
    }

    @Override
    public Book update(Book book) { //ТЕСТЫ НАПИСАН БРЕД
        log.info("Update book: {}", book.toString());
        int i;
        try {
            Object[] objects = new Object[]{
                    book.getName(),
                    book.getWrittenYear(),
                    book.getDescription(),
                    book.getId()
            };
            i = jdbcTemplate.update("UPDATE test.books SET name=?, writtenYear=?, description=?,  where id=?)",
                    objects);
        } catch (DataAccessException e) {
            i = 0;
        }
        return (i != 0) ? book : null;
    }

    @Override
    public Book getById(int id) throws DataAccessException {
        log.info("Get book by id: {}", id);
        Object[] objects = new Object[]{id};
        Book book = null;
        try {
            book = jdbcTemplate.queryForObject("select * from test.books where id = ?", objects, (rs, arg) -> setFields(rs));
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result in getting by id");
        }
        return book;
    }

    @Override
    public List<Book> getByName(String name) throws DataAccessException {
        log.info("Get book by name: {}", name);
        Object[] objects = new Object[]{name};
        List<Book> books = null;
        try {
            books = jdbcTemplate.query("select * from test.books where name = ?", objects, (rs, rowNum) -> setFields(rs));
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result in getting by name");
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM test.books", (rs, rowNum) -> setFields(rs));
    }

    @Override
    public Boolean deleteById(int id) {
        return jdbcTemplate.update("delete from test.books where id = ?", id) != 0;
    }

    @Override
    public Boolean deleteByName(String name) {
        return jdbcTemplate.update("delete from test.books where name = ?", name) != 0;
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.execute("delete from test.books;");
        } catch (DataAccessException e) {
            log.info("Empty result in all deleting");
        }
    }


    private Book setFields(ResultSet rs) throws SQLException {
        Book book1 = new Book();
        book1.setId(rs.getInt("id"));
        book1.setName(rs.getString("name"));
        book1.setDescription(rs.getString("description"));
        book1.setWrittenYear(rs.getInt("writtenYear"));
        book1.setReaded(rs.getBoolean("readed"));
        return book1;
    }

}
