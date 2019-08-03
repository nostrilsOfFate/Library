package lena.library.dao;

import lena.library.model.Author;
import lena.library.model.Genre;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class GenreDaoImpl implements GenreDao {

        protected final Log logger = LogFactory.getLog(lena.library.dao.GenreDaoImpl.class);
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
    public Genre insert(Genre genre) throws DataAccessException  { //ставка

        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into test.genres (name) values(?)", new String[]{"id"});
                ps.setString(1, genre.getName());
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        genre.setId(holder.getKey().intValue());
        return genre;
    }

    @Override
    public Genre update(Genre genre) { //обновление текущего пользователя
        int i;
        try {
            Object[] objects = new Object[]{
                    genre.getName(),
                    genre.getId(),
            };
            i = jdbcTemplate.update("UPDATE  test.genres SET name = ? where id = ?", objects);
        } catch (DataAccessException e) {
            i=0;
        }
        return (i!=0) ? genre : null;
    }

    @Override
    public Genre getById(int id) throws DataAccessException {
        Object[] objects = new Object[]{id};
        Genre genre = null;
        try {
            genre = jdbcTemplate.queryForObject("select * from test.genres where id = ?", objects, new RowMapper<Genre>() {
                public Genre mapRow(ResultSet rs, int arg) throws SQLException {
                    Genre genre1 = new Genre();
                    genre1.setId(rs.getInt("id"));
                    genre1.setName(rs.getString("name"));
                    return genre1;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result in getting by id");
        }
        return genre;
    }

    @Override
    public Genre getByName(String name) throws DataAccessException {
        Object[] objects = new Object[]{name};
        Genre genre = null;
        try {
            genre = jdbcTemplate.queryForObject("select * from test.genres where name = ?", objects, new RowMapper<Genre>() {
                public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Genre genre1 = new Genre();
                    genre1.setId(rs.getInt("id"));
                    genre1.setName(rs.getString("name"));
                    return genre1;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result in getting by name");
        }
        return genre;
    }

    @Override
    public List<Genre> getAllGenres() {
        return jdbcTemplate.query("SELECT * FROM test.genres", new RowMapper<Genre>() {
            public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                Genre genre1 = new Genre();
                genre1.setId(rs.getInt("id"));
                genre1.setName(rs.getString("name"));
                return genre1;
            }
        });
    }

    @Override
    public Boolean deleteById(int id) {
        jdbcTemplate.update("delete from test.genres where id = ?", id);
    }

    @Override
    public Boolean deleteByName(String name) {
        jdbcTemplate.update("delete from test.genres where name = ?", name);
    }



    public Boolean deleteAll() {
            jdbcTemplate.execute("delete from test.genres;");
        }

        public void createNewBase() {
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS test.genre (\n" +
                    "  id   INTEGER     NOT NULL AUTO_INCREMENT,\n" +
                    "  name VARCHAR(50) NOT NULL,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ");");
        }
    }
