package lena.library.dao;

import lena.library.model.Author;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthorDao {

    int insert(Author author);
    Author getById(int id) throws DataAccessException;
    Author getByName(String name) throws DataAccessException;
    List<Author> getAllAuthors();
   int deleteById(int id);
    int deleteByName(String name);
}
