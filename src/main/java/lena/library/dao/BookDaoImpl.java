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
            PreparedStatement ps = connection.prepareStatement("insert into test.book (name,description,writtenYear) values(?,?,?)", new String[]{book.getName(), book.getDescription(), book.getWrittenYear().toString()});
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
                    book.getId(),
            };
            i = jdbcTemplate.update("UPDATE  test.books  SET ( id, name, description, writtenYear) values(?,?,?,?) where id=: id", objects);
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
            book = jdbcTemplate.queryForObject("select from test.books where id = ?", objects, (rs, arg) -> setFields(rs));
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
            books = jdbcTemplate.query("select * from test.books where name = ?", objects, new RowMapper<Book>() {
                public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return setFields(rs);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result in getting by name");
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM test.books", new RowMapper<Book>() {
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                return setFields(rs);
            }
        });
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from test.books where id = ?", id);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from test.books where name = ?", name);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.execute("delete from test.books;");
    }

    @Override
    public void createNewBase() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS test.books (\n" +
                "  id   INTEGER     NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(50) NOT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ");");
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
