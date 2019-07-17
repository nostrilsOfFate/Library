package lena.library.Service;

import lena.library.dao.AuthorDao;
import lena.library.model.Author;
import lena.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao dao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.dao = authorDao;
    }


    @Override
    public Integer addAuthor(String name) {
       Author author = new Author(null,name);
       return dao.insert(author);
    }

    @Override
    public Integer deleteAuthorById(Integer id) {

        return dao.deleteById(id);
    }

    @Override
    public Integer deleteAuthorByName(String name) {
        return dao.deleteByName(name);
    }

    @Override
    public Integer updateAuthor(Integer id, String newName) {
        Author author = new Author(id,newName);
        return dao.insert(author);
    }

    @Override
    public Author getAuthorById(Integer id) {
        try {
            return dao.getById(id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Author getAuthorByName(String name) {
        try {
            return dao.getByName(name);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return dao.getAll();
    }
}
