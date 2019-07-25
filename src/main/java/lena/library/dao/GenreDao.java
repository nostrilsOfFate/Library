package lena.library.dao;

import lena.library.model.Author;
import lena.library.model.Genre;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface GenreDao {
    int insert(Genre genre);
    void update(Genre genre);
    Genre getById(int n) throws DataAccessException;
    Genre getByName(String name) throws DataAccessException;
    List<Genre> getAllGenre();
    void deleteById(int id);
    void deleteByName(String name);
}
