package libraryTests;
import lena.library.dao.AuthorDao;
import lena.library.model.Author;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.List;


@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
public class AuthorDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Qualifier(value = "jdbcTemplateAuthorDaoTest")
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
        authorDao.deleteAll();
        Author author = new Author("No Name");
       authorDao.insert(author);
        author.setName("Ananas");
        authorDao.update(author);
        assertEquals("Ananas", author.getName());
    }

    @Test
    public void deleteByIdTest() {
        Author author = new Author("test message");
        // Сохраняю тестовый объект в базе данных
        Author authorToDelete = authorDao.insert(author);
        // Вытаскиваю тестовый объект из базы данных
    //    Author authorToDelete = authorDao.g);
        // Удаляю тестовый объект из базы данных
        authorDao.deleteById(authorToDelete.getId());
        // Ищу удаленный объект в базе данных
        Author authorAfterDeleting = authorDao.getById(authorToDelete.getId());
        // сравнение вытащенного объекта после удаления из базы данных на null
        assertNull(authorAfterDeleting);
    }

    @Test
    public void deleteByNameTest() {
        Author author = new Author("test message");
        // Сохраняю тестовый объект в базе данных
        Author  authorToDelete = authorDao.insert(author);
        // Вытаскиваю тестовый объект из базы данных
        // Удаляю тестовый объект из базы данных
        authorDao.deleteByName(authorToDelete.getName());
        // Ищу удаленный объект в базе данных
        Author authorAfterDeleting = authorDao.getByName(authorToDelete.getName());
        // сравнение вытащенного объекта после удаления из базы данных на null
        assertNull(authorAfterDeleting);
    }

    @Test
    public void getByIdTest() throws DataAccessException { //переписать
        authorDao.deleteAll();
        Author author = new Author("No Name"); // создала автора
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
    @BeforeEach
    public void deleteAll() {
        System.out.println("Before test1");
        authorDao.deleteAll();
        System.out.println("Before test2");
    }

//    @AfterEach
//    public void createEmptyBase() {
//        System.out.println("After test1");
//        authorDao.createNewBase();
//        System.out.println("After test2");
//    }
}
