package lena.library.dao;

import lena.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthorDao {

    int insert(Author author); //вставить, добавить нового

    void update(Author author);

    Author getById(int id) throws DataAccessException;

    Author getByName(String name) throws DataAccessException;

    List<Author> getAllAuthors();

    void deleteById(int id);

    void deleteByName(String name);

    void deleteAll();

    void createNewBase();
}
