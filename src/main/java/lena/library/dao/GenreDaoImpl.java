package lena.library.dao;

import lena.library.model.Author;
import lena.library.model.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert(Genre genre) {
        if (genre.isNew()) {
            return namedParameterJdbcOperations.getJdbcOperations().update("insert into data_genre.genre (name) values(?)", genre.getName());
        } else {
            return namedParameterJdbcOperations.getJdbcOperations().update("update data_genre.genre set name=? where id=?", genre.getName(), genre.getId());
        }
    }

    @Override
    public Genre getById(int id) throws DataAccessException {
        Map<String, Integer> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select * from data_genre.genre where id = :id", params, new GenreDaoImpl.GenreMapper());
    }

    @Override
    public Genre getByName(String name) throws DataAccessException {
        Map<String, String> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.queryForObject("select * from data_genre.genre where name = :name", params, new GenreDaoImpl.GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select * from data_genre.genre", new GenreDaoImpl.GenreMapper());
    }

    @Override
    public int deleteById(int id) {
        Map<String, Integer> params = Collections.singletonMap("name", id);
        return namedParameterJdbcOperations.update("delete * from data_genre.genre where name = :name", params);
    }

    @Override
    public int deleteByName(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.update("DELETE * from data_genre.genre where name = :name", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
