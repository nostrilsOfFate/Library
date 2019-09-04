package lena.library.dao;

import lena.library.model.Author;
import lena.library.model.Genre;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface GenreDao {
    Genre insert(Genre genre);

    Genre update(Genre genre);

    Genre getById(int id) throws DataAccessException;

    Genre getByName(String name) throws DataAccessException;

    List<Genre> getAllGenres();

    Boolean deleteById(int id);

    Boolean deleteByName(String name);

    void deleteAll();

}
