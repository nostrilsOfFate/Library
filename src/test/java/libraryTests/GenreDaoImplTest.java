package libraryTests;
import lena.library.dao.AuthorDao;
import lena.library.dao.GenreDao;
import lena.library.model.Author;
import lena.library.model.Genre;
import org.junit.Test;
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
public class GenreDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Qualifier(value = "jdbcTemplateGenreDaoTest")
    @Autowired
    //   @Mock
    private GenreDao genreDao;

    @Test
    public void insertTest() {
        Genre expected = new Genre(null, "Вася Пупкин");
        Genre actual = genreDao.insert(expected);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void updateTest() {
        genreDao.deleteAll();
        Genre genre = new Genre("No Name");
        genreDao.insert(genre);
        genre.setName("Dramisha");
        genreDao.update(genre);
        assertEquals("Dramisha", genre.getName());
    }

    @Test
    public void deleteByIdTest() {
        Genre genre = new Genre("test message");
        // Сохраняю тестовый объект в базе данных
        Genre genreToDelete = genreDao.insert(genre);
        // Вытаскиваю тестовый объект из базы данных
        // Удаляю тестовый объект из базы данных
        genreDao.deleteById(genreToDelete.getId());
        // Ищу удаленный объект в базе данных
        Genre genreAfterDeleting = genreDao.getById(genreToDelete.getId());
        // сравнение вытащенного объекта после удаления из базы данных на null
        assertNull(genreAfterDeleting);
    }

    @Test
    public void deleteByNameTest() {
        Genre genre = new Genre("test message");
        Genre  genreToDelete = genreDao.insert(genre);
        genreDao.deleteByName(genreToDelete.getName());
        Genre genreAfterDeleting = genreDao.getByName(genreToDelete.getName());
        assertNull(genreAfterDeleting);
    }

    @Test
    public void getByIdTest() throws DataAccessException { //переписать
        genreDao.deleteAll();
        Genre genre = new Genre("No Name"); // создала автора
        genreDao.insert(genre);// созранила его и вернула
        //у возвращенного автора взять ид и испоьльзовать его для поиска по ид
        Genre genre1 = genreDao.getById(genre.getId());
        assertEquals(genre.getName(), genre1.getName());
    }

    @Test
    public void getByNameTest() throws DataAccessException { //переписать
        genreDao.deleteAll();
        Genre genre = new Genre();
        genre.setName("No Name");
        genreDao.insert(genre);
        Genre genre1 = genreDao.getByName(genre.getName());
        assertEquals(genre.getName(), genre1.getName());
    }

    @Test
    public void getAllAuthors() throws DataAccessException {
        genreDao.deleteAll();
        List<Genre> expected = new ArrayList<>();
        Genre genre1 = new Genre("Стихи");
        Genre genre2 = new Genre("Триллер");
        Genre genre3 = new Genre("Сказка");
        expected.add(genre1);
        expected.add(genre2);
        expected.add(genre3);
        genreDao.insert(genre1);
        genreDao.insert(genre2);
        genreDao.insert(genre3);
        List<Genre> actual = genreDao.getAllGenres();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void deleteAllTest() throws DataAccessException {
        //пустой лист
        List<Genre> expected = new ArrayList<>();
        //удаляем все строки в дао
        genreDao.deleteAll();
        // проверяем длинну листа
        List<Genre> actual = genreDao.getAllGenres();
        //сравниваю значение
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void createNewBaseTest() throws DataAccessException {
        List<Genre> expected = new ArrayList<>();
        genreDao.createNewBase();
        List<Genre> actual = genreDao.getAllGenres();
        assertEquals(expected.size(), actual.size());
    }
//    @BeforeEach
//    public void deleteAll() {
//        System.out.println("Before test1");
//        authorDao.deleteAll();
//        System.out.println("Before test2");
//    }
//    @AfterEach
//    public void createEmptyBase() {
//        System.out.println("After test1");
//        authorDao.createNewBase();
//        System.out.println("After test2");
//    }
}
