package lena.library.dao;

import lena.library.model.Author;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.Database;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.*;
import java.util.List;

//@Slf4j
//@Transactional
@Repository
public class AuthorDaoImpl implements AuthorDao {
    //  public EntityManager em = Persistence.createEntityManagerFactory("LenaTest").createEntityManager();
//    // private NamedParameterJdbcOperations namedParameterJdbcOperations;
//    @Autowired
//    private DataSource dataSource;
//    private JdbcTemplate jdbcTemplate;
//
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    protected final Log logger = LogFactory.getLog(AuthorDaoImpl.class);


    @Mock
    private DataSource dataSource;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int insert(Author author) { //ставка

        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into test.authors (name) values(?)", new String[]{"id"});
                ps.setString(1, author.getName());
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        return holder.getKey().intValue();
    }

    @Override
    public void update(Author author) { //обновление текущего пользователя
        Object[] objects = new Object[]{
                author.getName(),
                author.getId()
        };
        jdbcTemplate.update("UPDATE  test.authors SET name= ? where id= ?", objects);
    }

    @Override
    public Author getById(int id) throws DataAccessException {
        Object[] objects = new Object[]{id};
        Author author = null;
        try {
            author = jdbcTemplate.queryForObject("select * from test.authors where id = ?", objects, new RowMapper<Author>() {
                public Author mapRow(ResultSet rs, int arg) throws SQLException {
                    Author author = new Author();
                    author.setId(rs.getInt("id"));
                    author.setName(rs.getString("name"));
                    return author;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result in getting by id");
        }
        return author;
    }

    @Override
    public Author getByName(String name) throws DataAccessException {
        Object[] objects = new Object[]{name};
        Author author = null;
        try {
            author = jdbcTemplate.queryForObject("select * from test.authors where name = ?", objects, new RowMapper<Author>() {
                public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Author author = new Author();
                    author.setId(rs.getInt("id"));
                    author.setName(rs.getString("name"));
                    return author;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result in getting by name");
        }
        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM test.authors", new RowMapper<Author>() {
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                return author;
            }
        });
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from test.authors where id = ?", id);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from test.authors where name = ?", name);
    }

//    @Override
//    public long insert(Author author) {
//        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection.prepareStatement("insert into data_genre.authors (name) values(?)", new String[]{author.getName()});
//                ps.setString(1, author.getName());
//                ps.setLong(2, author.getId());
//                return ps;
//            }
//        };
//        KeyHolder holder = new GeneratedKeyHolder();
//        jdbcTemplate.update(preparedStatementCreator, holder);
//        return holder.getKey().longValue();
////        Connection conn = null;
////        int i = 0;
////
////        if (author.isNew()) {
////            try {
////                String SQL = "insert into authors (name) values(?)";
////                conn = dataSource.getConnection();
////                PreparedStatement ps = conn.prepareStatement(SQL);
////                jdbcTemplate.update(SQL, author.getName());
////                log.info("Author successfully created.\nName: " + author.getName() + ";\nId: " +
////                        author.getId() + ";\nBooks: " + author.getBooks() + "\n");
////                i = 1;
////                ps.executeUpdate();
////                ps.close();
////            } catch (SQLException e) {
////                throw new RuntimeException(e);
////            } finally {
////                //каждый раз закрываем соединение
////                if (conn != null) {
////                    try {
////                        conn.close();
////                    } catch (SQLException e) {
////                    }
////                }
////            }
////        }
//////            return namedParameterJdbcOperations.getJdbcOperations().update("insert into data_genre.authors (name) values(?)", author.getName());
////                else {
////                    try {
////                        String SQL = "update authors set name=? where id=?";
////                        conn = dataSource.getConnection();
////                        PreparedStatement ps = conn.prepareStatement(SQL);
////                        jdbcTemplate.update(SQL, author.getName(), author.getId());
////                        log.info("Author successfully created.\nName: " + author.getName() + ";\nId: " +
////                                author.getId() + ";\nBooks: " + author.getBooks() + "\n");
////                        i = 0;
////                        ps.executeUpdate();
////                        ps.close();
////                    } catch (SQLException e) {
////                        throw new RuntimeException(e);
////                    } finally {//каждый раз закрываем соединение
////                        if (conn != null) {
////                            try { conn.close();
////                            } catch (SQLException e) {}
////                        }
//////            return namedParameterJdbcOperations.getJdbcOperations().update("update data_genre.authors set name=? where id=?", author.getName(), author.getId());
////                    }
////                }return i;
//    }
//
//    @Override
//    public long update(Author author) {
//        return 0;
//    }
//
//
//    @Override
//    public Author getById(int id) throws DataAccessException {
////        Map<String, Integer> params = Collections.singletonMap("id", id);
////        return namedParameterJdbcOperations.queryForObject("select * from data_genre.authors where id = :id", params, new AuthorMapper());
//        return em.find(Author.class, id);
//    }
//
//    @Override
//    public Author getByName(String name) throws DataAccessException {
////        Map<String, String> params = Collections.singletonMap("name", name);
////        return namedParameterJdbcOperations.queryForObject("select * from data_genre.authors where name = :name", params, new AuthorMapper());
//        return null;
//    }
//
//    @Override
//    public List<Author> getAllAuthors() {
////        return namedParameterJdbcOperations.query("select * from data_genre.authors", new AuthorMapper());
//        return null;
//    }
//
//    @Override
//    public void deleteById(int id) {
////        Map<String, Integer> params = Collections.singletonMap("name", id);
////////        return namedParameterJdbcOperations.update("delete * from data_genre.authors where name = :name", params);
//
//    }
//
//    @Override
//    public void deleteByName(String name) {
////        Map<String, String> params = Collections.singletonMap("name", name);
////        return namedParameterJdbcOperations.update("DELETE * from data_genre.authors where name = :name", params);
//
//    }
//
//    private static class AuthorMapper implements RowMapper<Author> {
//        @Override
//        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            return new Author(id, name);
//        }
//    }

    public void deleteAll() {
        jdbcTemplate.execute("delete from test.authors;");
    }

    public void createNewBase() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS data_genre.authors (\n" +
                "  id   INTEGER     NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(50) NOT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ");");
    }
}
