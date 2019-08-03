package lena.library.dao;

import lena.library.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthorDao {

    Author insert(Author author); //вставить, добавить нового

    Author update(Author author);

    Author getById(int id) throws DataAccessException;

    Author getByName(String name) throws DataAccessException;

    List<Author> getAllAuthors();

    Boolean deleteById(int id);

    Boolean deleteByName(String name);

    Boolean deleteAll();

    void createNewBase();
}
