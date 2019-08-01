package libraryTests;//import lena.library.dao.AuthorDao;
//
//import org.junit.Test;
//import org.junit.internal.runners.JUnit4ClassRunner;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import static org.junit.Assert.assertEquals;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import lena.library.dao.AuthorDao;
import lena.library.dao.AuthorDaoImpl;
import lena.library.model.Author;
import org.hibernate.dialect.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
//import play.api.db.Databases;
import java.util.ArrayList;
import java.util.List;


//@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class})

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
//@ContextConfiguration("classpath:spring/spring-context.xml")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@RunWith(MockitoJUnitRunner.class)
public class AuthorDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Qualifier(value = "jdbcTemplateAuthorDaoTest")
    @Autowired
    //   @Mock
    private AuthorDao authorDao;
//    Database database;

//    @Before
//    public void createDatabase() {
//        database = Databases.inMemory();
//       // database = Databases.createFrom("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test");
//    }
//
//    @After
//    public void shutdownDatabase() {
//        database.shutdown();
//    }


    @Test
    public void insertTest() {
        Author expected = new Author(null, "Вася Пупкин");
        int id = authorDao.insert(expected);
        Author actual = authorDao.getById(id);
        assertEquals(expected.getName(), actual.getName());
//        Author expected = mock(Author.class);
//        Mockito.when(authorDao.insert(new ;
//        int id = authorDao.insert(expected);
//        assertEquals(1, id);

    }

    @Test
    public void updateTest() {
        Author author = new Author("No Name");
     //   authorDao.insert(author);
        Author author1 = new Author("Ananas");
        authorDao.update(author);
        authorDao.insert(author1);
        assertEquals(author.getName(), author1.getName());
    }

    @Test
    public void deleteByIdTest() {

        Author author = new Author();
        author.setName("test message");
        // Сохраняю тестовый объект в базе данных
        int id = authorDao.insert(author);
        // Вытаскиваю тестовый объект из базы данных
        Author authorToDelete = authorDao.getById(id);
        // Удаляю тестовый объект из базы данных
        authorDao.deleteById(authorToDelete.getId());
        // Ищу удаленный объект в базе данных
        Author authorAfterDeleting = authorDao.getById(id);
        // сравнение вытащенного объекта после удаления из базы данных на null
        assertNull(authorAfterDeleting);
    }

    @Test
    public void deleteByNameTest() {
        Author author = new Author();
        author.setName("test message");
        // Сохраняю тестовый объект в базе данных
        int id = authorDao.insert(author);
        String name = author.getName();
        // Вытаскиваю тестовый объект из базы данных
        Author authorToDelete = authorDao.getByName(name);
        // Удаляю тестовый объект из базы данных
        authorDao.deleteByName(authorToDelete.getName());
        // Ищу удаленный объект в базе данных
        Author authorAfterDeleting = authorDao.getByName(name);
        // сравнение вытащенного объекта после удаления из базы данных на null
        assertNull(authorAfterDeleting);

    }

    @Test
    public void getByIdTest() throws DataAccessException { //переписать
        authorDao.deleteAll();
        Author author = new Author("No Name"); // создала автора
//    Author author1 = serviceAuthor.addAuthor(author.getName());
//    Author authorFromDB = serviceAuthor.getAuthorById(author1.getId());
        authorDao.insert(author);// созранила его и вернула
        //у возвращенного автора взять ид и испоьльзовать его для поиска по ид
        Author author1 = authorDao.getById(author.getId());
        assertEquals(author.getName(), author1.getName());
    }

    @Test
    public void getByNameTest() throws DataAccessException { //переписать
        authorDao.deleteAll();
        Author author = new Author();
        author.setName("No Name");
        authorDao.insert(author);
        Author author1 = authorDao.getByName(author.getName());
        assertEquals(author.getName(), author1.getName());
    }


    @Test
    public void getAllAuthors() throws DataAccessException {
        authorDao.deleteAll();
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
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void deleteAllTest() throws DataAccessException {
        //пустой лист
        List<Author> expected = new ArrayList<>();
        //удаляем все строки в дао
        authorDao.deleteAll();
        // проверяем длинну листа
        List<Author> actual = authorDao.getAllAuthors();
        //сравниваю значение
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void createNewBaseTest() throws DataAccessException {
        List<Author> expected = new ArrayList<>();
        authorDao.createNewBase();
        List<Author> actual = authorDao.getAllAuthors();
        assertEquals(expected.size(), actual.size());
    }

//    @BeforeEach
//    public void deleteAll() {
//        System.out.println("Before test1");
//        authorDao.deleteAll();
//        System.out.println("Before test2");
//    }
//
//    @AfterEach
//    public void createEmptyBase() {
//        System.out.println("After test1");
//        authorDao.createNewBase();
//        System.out.println("After test2");
//    }
}
