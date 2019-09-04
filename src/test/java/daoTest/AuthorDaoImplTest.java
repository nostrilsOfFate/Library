package daoTest;

import lena.library.dao.AuthorDao;
import lena.library.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;


@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
public class AuthorDaoImplTest {//extends AbstractJUnit4SpringContextTests

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void insertTest() {
        Author expected = new Author(null, "Вася Пупкин");
        Author actual = authorDao.insert(expected);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void updateTest() {
        Author author = authorDao.getAllAuthors().get(0);
        author.setName("Ananas S.S");
        authorDao.update(author);
        assertEquals("Ananas S.S", author.getName());
    }

    @Test
    public void deleteByIdTest() {
        Author author = authorDao.getAllAuthors().get(0);
        authorDao.deleteById(author.getId());
        Author authorAfterDeleting = authorDao.getById(author.getId());
        assertNull(authorAfterDeleting);
    }

    @Test
    public void deleteByNameTest() {
        Author author = authorDao.getAllAuthors().get(0);
        authorDao.deleteByName(author.getName());
        Author authorAfterDeleting = authorDao.getByName(author.getName());
        assertNull(authorAfterDeleting);
    }

    @Test
    public void getByIdTest() throws DataAccessException {
        Author author = authorDao.getAllAuthors().get(0);
        Author author1 = authorDao.getById(author.getId());
        assertEquals(author.getName(), author1.getName());
    }

    @Test
    public void getByNameTest() throws DataAccessException { //переписать
        Author author =authorDao.getAllAuthors().get(0);
        Author author1 = authorDao.getByName(author.getName());
        assertEquals(author.getName(), author1.getName());
    }

    @Test
    public void getAllAuthorsTest() throws DataAccessException {
        List<Author> expected = new ArrayList<>();
        Author author1 = new Author("Пушкин");
        Author author2 = new Author("Кинг");
        Author author3 = new Author("Сноуден");
        expected.add(author1);
        expected.add(author2);
        expected.add(author3);
        authorDao.insert(author1);
        authorDao.insert(author2);
        authorDao.insert(author3);
        List<Author> actual = authorDao.getAllAuthors();
        assertEquals(expected.size()+1, actual.size());
    }

    @Test
    public void deleteAllTest() throws DataAccessException {
        authorDao.deleteAll();
        List<Author> actual = authorDao.getAllAuthors();
        assertEquals(0, actual.size());
    }



    @BeforeEach
    public void init() {
        Author author1 = new Author(null, "Александрова Е.В.");
        authorDao.insert(author1);
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Before test1");
        authorDao.deleteAll();
        System.out.println("Before test2");
    }

}
