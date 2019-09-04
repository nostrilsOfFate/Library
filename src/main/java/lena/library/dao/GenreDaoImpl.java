package lena.library.dao;

import lena.library.model.Genre;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class GenreDaoImpl implements GenreDao {


        private DataSource dataSource;

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
        try {
            genre.setId(holder.getKey().intValue());
        } catch (InvalidDataAccessApiUsageException e) {
            log.info("Invalid value of key");
        }
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
            log.info("Empty result in getting by id");
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
            log.info("Empty result in getting by name");
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
    public Boolean deleteById(int id)  throws DataAccessException {
        return jdbcTemplate.update("delete from test.genres where id = ?", id)!=0;
    }

    @Override
    public Boolean deleteByName(String name)  throws DataAccessException {
            return jdbcTemplate.update("delete from test.genres where name = ?", name)!=0;
    }

    public void deleteAll() {
        try {
            jdbcTemplate.execute("delete from test.genres;");
        } catch (DataAccessException e) {
            log.info("Empty result in all deleting");
        }
    }

    }
