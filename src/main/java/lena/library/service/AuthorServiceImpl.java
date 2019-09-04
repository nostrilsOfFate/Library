package lena.library.service;

import lena.library.dao.AuthorDao;
import lena.library.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author addAuthor(String name) {
        Author author = new Author(null, name);
        return authorDao.insert(author);
    }

    @Override
    public Boolean deleteAuthorById(Integer id) {
        return authorDao.deleteById(id);
    }

    @Override
    public Boolean deleteAuthorByName(String name) {
        return authorDao.deleteByName(name);
    }

    @Override
    public Author updateAuthor(Integer id, String newName) {
        Author author = authorDao.getById(id);
        author.setName(newName);
        return authorDao.update(author);
    }

    @Override
    public Author getAuthorById(Integer id) {
            return authorDao.getById(id);
    }

    @Override
    public Author getAuthorByName(String name) {
       return authorDao.getByName(name);
    }

    @Override
    public List<Author> getAllAuthors() {
        try {
            return authorDao.getAllAuthors();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
