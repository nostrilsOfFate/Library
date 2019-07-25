package lena.library.dao;

import lena.library.model.Author;
import lena.library.model.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Component
public class BookDaoImpl implements BookDao {

    protected final Log logger = LogFactory.getLog(BookDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int insert(Book book) { //
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into data_genre.book (name,description,writtenYear) values(?,?,?)", new String[]{book.getName(), book.getDescription(), book.getWrittenYear().toString()});
                ps.setString(1, book.getName());
                ps.setString(2, book.getDescription());
                ps.setInt(3, book.getWrittenYear()); //очень спорный момент, будет ли работать вопрос  - значение инта
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        return holder.getKey().intValue();
    }

    @Override
    public void update(Book book) { //что именно делает? все обновлять? ВОЗМОЖНО НЕ ВЕРНО
        Object[] objects = new Object[]{
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getWrittenYear()
        };
        jdbcTemplate.update("UPDATE  data_genre.books  SET ( id, name, description, writtenYear) values(?,?,?,?) where id=: id", objects);
    }

    @Override
    public Book getById(int id) throws DataAccessException {
        Book book = null;
        try {
            book = jdbcTemplate.queryForObject("select * from data_genre.books where id = :id", new Object[]{id}, new RowMapper<Book>() {
                public Book mapRow(ResultSet rs, int arg) throws SQLException {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    //работает при удаленном любом пункте, надо сделать рефакторинг
                    book.setName(rs.getString("name"));
                    book.setDescription(rs.getString("description"));
                    book.setWrittenYear(rs.getInt("writtenYear"));
                    book.setReaded(rs.getBoolean("readed"));
                    return book;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result in getting by id");
        }
        return book;
    }

    @Override
    public List<Book> getByName(String name) throws DataAccessException {
        Object[] objects = new Object[]{name};
        List<Book> books = new ArrayList<>();
        try {
            books = jdbcTemplate.query("select * from data_genre.books where name = :name", objects, new RowMapper<Book>() {
                public Book mapRow(ResultSet rs, int arg) throws SQLException {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    //работает при удаленном любом пункте, надо сделать рефакторинг
                    book.setName(rs.getString("name"));
                    book.setDescription(rs.getString("description"));
                    book.setWrittenYear(rs.getInt("writtenYear"));
                    book.setReaded(rs.getBoolean("readed"));
                    return book;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result in getting by name");
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM data_genre.books", new RowMapper<Book>() {
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setDescription(rs.getString("description"));
                book.setWrittenYear(rs.getInt("writtenYear"));
                book.setReaded(rs.getBoolean("readed"));
                //как подтянуть списки пока не ясно, такого столбца нету,
                // зато эта инфа есть в соседней таблице
                return book;
            }
        });
    }

    public Book mapRow(ResultSet rs, int arg) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        //работает при удаленном любом пункте, надо сделать рефакторинг
        book.setName(rs.getString("name"));
        book.setDescription(rs.getString("description"));
        book.setWrittenYear(rs.getInt("writtenYear"));
        book.setReaded(rs.getBoolean("readed"));
        return book;
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete * from data_genre.books where id = :id", id);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete * from data_genre.books where name = :name", name);
    }

//    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
//
//    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
//        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
//    }
//
//    @Override
//    public int insert(Book book) {
//        if (book.isNew()) {//"insert into books (name, description, author, genre) values (?, ?, ?, ?) ",
//          //  book.getName(), book.getDescription(), book.getAuthor().getName(),
//                  //  book.getGenre().getName()
//            return namedParameterJdbcOperations.getJdbcOperations().
//                    update("insert into data_genre.book (name,writtenYear,description) values(?,?,?)", book.getName(), book.getWrittenYear(),book.getDescription());
//        } else {
//            return namedParameterJdbcOperations.getJdbcOperations().update("update books set name=?,writtenYear=?, description=? where id=?",
//                    book.getName(),book.getWrittenYear(), book.getDescription(), book.getId());
//        }
//    }
//
//    @Override
//    public Book getById(int id) throws DataAccessException {
//        Map<String, Integer> params = Collections.singletonMap("id", id);
//        return namedParameterJdbcOperations.queryForObject("select * from data_genre.book where id = :id", params, new BookDaoImpl.BookMapper());
//    }
//
//    @Override
//    public List<Book> getByName(String name) throws DataAccessException {
//        Map<String, String> params = Collections.singletonMap("name", name);
//        //queryForObject("select * from data_genre.book where name = :name", params, new BookDaoImpl.BookMapper());
//        return (List<Book>) namedParameterJdbcOperations.queryForList("select * from data_genre.book where name = :name",
//               params, Book.class);
//
//    }
//
//    @Override
//    public List<Book> getAllBooks() {
//        return namedParameterJdbcOperations.query("select * from data_genre.book", new BookDaoImpl.BookMapper());
//    }
//
//    @Override
//    public int deleteById(int id) {
//        Map<String, Integer> params = Collections.singletonMap("name", id);
//        return namedParameterJdbcOperations.update("delete * from data_genre.book where name = :name", params);
//    }
//
//    @Override
//    public int deleteByName(String name) {
//        Map<String, String> params = Collections.singletonMap("name", name);
//        //update("DELETE * from data_genre.book where name = :name", params);
//        return namedParameterJdbcOperations.update("DELETE * from data_genre.book where name = :name", params);
//
//    }
//    private static class BookMapper implements RowMapper<Book> {
//        @Override
//        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            int writtenYear = resultSet.getInt("writtenYear");
//            String description = resultSet.getString("description");
//            Set<Author> authors = new HashSet<Author>(); // ?
//            Set<Genre> genres = new HashSet<Genre>();
//            //таки шо делать с авторами и жанрами в списках, списки то пустые!
//          //  Genre genre = new Genre(null, resultSet.getString("genre"));
//            return new Book(id, name, writtenYear,description, authors, genres);
//        }
//    }
}
