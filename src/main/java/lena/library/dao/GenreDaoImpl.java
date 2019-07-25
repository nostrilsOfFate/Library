package lena.library.dao;

import lena.library.model.Genre;
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
import java.util.List;

@Component
public class GenreDaoImpl implements GenreDao {

    protected final Log logger = LogFactory.getLog(GenreDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int insert(Genre genre) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into data_genre.genres (name) values(?)", new String[]{"id"});
                ps.setString(1, genre.getName());
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        return holder.getKey().intValue();
    }

    @Override
    public void update(Genre genre) {
        Object[] objects = new Object[] {
                genre.getName(),
                genre.getId()
        };
        jdbcTemplate.update("UPDATE  data_genre.genres  SET name=? where id=?", objects);
    }

    @Override
    public Genre getById(int id) throws DataAccessException {
        Genre genre = null;
        try {
            genre = jdbcTemplate.queryForObject("select * from data_genre.genres where id = :id", new Object[]{id}, new RowMapper<Genre>() {
                public Genre mapRow(ResultSet rs, int arg) throws SQLException {
                    Genre genre = new Genre();
                    genre.setId(rs.getInt("id"));
                    genre.setName(rs.getString("name"));
                    return genre;
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
            genre = jdbcTemplate.queryForObject("select * from data_genre.genres where name = :name", objects, new RowMapper<Genre>() {
                public Genre mapRow(ResultSet rs, int arg) throws SQLException {
                    Genre genre = new Genre();
                    genre.setId(rs.getInt("id"));
                    genre.setName(rs.getString("name"));
                    return genre;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result in getting by name");
        }
        return genre;
    }

    @Override
    public List<Genre> getAllGenre() {
        return jdbcTemplate.query("SELECT * FROM data_genre.genres", new RowMapper<Genre>() {
            public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("name"));
                return genre;
            }
        });
    }

    @Override
    public void deleteById(int id) {

        jdbcTemplate.update("delete * from data_genre.genres where id = :id", id);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete * from data_genre.genres where name = :name", name);
    }
//
//    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
//
//    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
//        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
//    }
//
//    @Override
//    public int insert(Genre genre) {
//        if (genre.isNew()) {
//            return namedParameterJdbcOperations.getJdbcOperations().update("insert into data_genre.genre (name) values(?)", genre.getName());
//        } else {
//            return namedParameterJdbcOperations.getJdbcOperations().update("update data_genre.genre set name=? where id=?", genre.getName(), genre.getId());
//        }
//    }
//
//    @Override
//    public Genre getById(int id) throws DataAccessException {
//        Map<String, Integer> params = Collections.singletonMap("id", id);
//        return namedParameterJdbcOperations.queryForObject("select * from data_genre.genre where id = :id", params, new GenreDaoImpl.GenreMapper());
//    }
//
//    @Override
//    public Genre getByName(String name) throws DataAccessException {
//        Map<String, String> params = Collections.singletonMap("name", name);
//        return namedParameterJdbcOperations.queryForObject("select * from data_genre.genre where name = :name", params, new GenreDaoImpl.GenreMapper());
//    }
//
//    @Override
//    public List<Genre> getAllGenre() {
//        return namedParameterJdbcOperations.query("select * from data_genre.genre", new GenreDaoImpl.GenreMapper());
//    }
//
//    @Override
//    public int deleteById(int id) {
//        Map<String, Integer> params = Collections.singletonMap("name", id);
//        return namedParameterJdbcOperations.update("delete * from data_genre.genre where name = :name", params);
//    }
//
//    @Override
//    public int deleteByName(String name) {
//        Map<String, String> params = Collections.singletonMap("name", name);
//        return namedParameterJdbcOperations.update("DELETE * from data_genre.genre where name = :name", params);
//    }
//
//    private static class GenreMapper implements RowMapper<Genre> {
//        @Override
//        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            return new Genre(id, name);
//        }
//    }
}
