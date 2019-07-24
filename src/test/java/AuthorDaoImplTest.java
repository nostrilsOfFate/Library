//import lena.library.dao.AuthorDao;
//
//import org.junit.Test;
//import org.junit.internal.runners.JUnit4ClassRunner;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import static org.junit.Assert.assertEquals;


import lena.library.Service.AuthorServiceImpl;
import lena.library.dao.AuthorDao;
import lena.library.model.Author;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4ClassRunner.class)
public class AuthorDaoImplTest {
@Autowired
    private AuthorDao authorDao;
    AuthorServiceImpl serviceAuthor = new AuthorServiceImpl(authorDao);

//    @Test
//   public void insertTest() {
//        Author expected = new Author(null, "Вася Пупкин");
//        authorDao.insert(expected);
//        Author actual = authorDao.getByName("Вася Пупкин");
//        assertEquals(expected.getName(), actual.getName());
//    }

@Test
    public void getById(int id) throws DataAccessException {
    Author author = new Author();
    author.setName("No Name");
    Author author1 = serviceAuthor.addAuthor(author.getName());
    Author authorFromDB = serviceAuthor.getAuthorById(author1.getId());
    assertEquals("Проблема в получении по айди автора", author1,authorFromDB);
 }
}
