package libraryTests;//import lena.library.dao.AuthorDao;
//
//import org.junit.Test;
//import org.junit.internal.runners.JUnit4ClassRunner;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import static org.junit.Assert.assertEquals;


import lena.library.dao.AuthorDao;
import lena.library.dao.AuthorDaoImpl;
import lena.library.model.Author;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import static org.junit.Assert.assertEquals;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = AuthorDaoImpl.class)
@ExtendWith(SpringExtension.class)

public class AuthorDaoImplTest extends AbstractJUnit4SpringContextTests {

@Autowired
    private AuthorDao authorDao;

   @Test
   public void insertTest() {
        Author expected = new Author(null, "Вася Пупкин");
        authorDao.insert(expected);
        Author actual = authorDao.getByName("Вася Пупкин");
        assertEquals(expected.getName(), actual.getName());
    }

@Test
    public void getByIdTest() throws DataAccessException { //переписать
   Author author = new Author();
   author.setName("No Name");
//    Author author1 = serviceAuthor.addAuthor(author.getName());
//    Author authorFromDB = serviceAuthor.getAuthorById(author1.getId());
    authorDao.insert(author);
    Author author1 = authorDao.getById(author.getId());
    assertEquals("Проблема в получении по айди автора", author,author1);
 }

 @Test
    public void getAllAuthors() throws DataAccessException {
     List<Author> expected = new ArrayList<>();
     expected.add(new Author("Пушкин"));
     expected.add(new Author("Кинг"));
     List<Author> actual = authorDao.getAllAuthors();
     assertEquals(expected.size(), actual.size());
 }



}
