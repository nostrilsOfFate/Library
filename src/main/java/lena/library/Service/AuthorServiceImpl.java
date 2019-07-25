//package lena.library.Service;
//
//import lena.library.dao.AuthorDao;
//
//import lena.library.model.Author;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AuthorServiceImpl implements AuthorService {
//    protected final Log logger = LogFactory.getLog(AuthorServiceImpl.class);
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public AuthorServiceImpl(AuthorDao authorDao) {
//        this.dao = authorDao;
//    }
//
//
//    @Override
//    public Author addAuthor(String name) {
//        Author author = new Author(null, name);
//
//        if (dao.insert(author) == 1) {
//            return author;
//        } else
//            throw new ChangeExсeption("Exсeption in adding authors");
//    }
//
//    @Override
//    public Integer deleteAuthorById(Integer id) {
//
//        return dao.deleteById(id);
//    }
//
//    @Override
//    public Integer deleteAuthorByName(String name) {
//        return dao.deleteByName(name);
//    }
//
//    @Override
//    public Integer updateAuthor(Integer id, String newName) {
//        Author author = new Author(id, newName);
//        return dao.insert(author);
//    }
//
//    @Override
//    public Author getAuthorById(Integer id) {
//        try {
//            return dao.getById(id);
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public Author getAuthorByName(String name) {
//        try {
//            return dao.getByName(name);
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public List<Author> getAllAuthors() {
//        return dao.getAllAuthors();
//    }
//}
