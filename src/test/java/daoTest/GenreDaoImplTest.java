package daoTest;
import lena.library.dao.GenreDao;
import lena.library.model.Author;
import lena.library.model.Genre;
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
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.List;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
public class GenreDaoImplTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void insertTest() {
        Genre expected = new Genre(null, "Вася Пупкин");
        Genre actual = genreDao.insert(expected);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void updateTest() {
        Genre genre = genreDao.getAllGenres().get(0);
        genre.setName("Dramisha");
        genreDao.update(genre);
        assertEquals("Dramisha", genre.getName());
    }

    @Test
    public void deleteByIdTest() {
        Genre genre = genreDao.getAllGenres().get(0);
        genreDao.deleteById(genre.getId());
        Genre genreAfterDeleting = genreDao.getById(genre.getId());
        assertNull(genreAfterDeleting);
    }

    @Test
    public void deleteByNameTest() {
        Genre genre = genreDao.getAllGenres().get(0);
        genreDao.deleteByName(genre.getName());
        Genre genreAfterDeleting = genreDao.getByName(genre.getName());
        assertNull(genreAfterDeleting);
    }

    @Test
    public void getByIdTest() throws DataAccessException { //переписать
        Genre genre = genreDao.getAllGenres().get(0);
        Genre genre1 = genreDao.getById(genre.getId());
        assertEquals(genre.getName(), genre1.getName());
    }

    @Test
    public void getByNameTest() throws DataAccessException { //переписать
        Genre genre = genreDao.getAllGenres().get(0);
        Genre genre1 = genreDao.getByName(genre.getName());
        assertEquals(genre.getName(), genre1.getName());
    }

    @Test
    public void getAllAuthors() throws DataAccessException {
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
        assertEquals(expected.size()+1, actual.size());
    }

    @Test
    public void deleteAllTest() throws DataAccessException {
        genreDao.deleteAll();
        List<Genre> actual = genreDao.getAllGenres();
        assertEquals(0, actual.size());
    }


    @BeforeEach
    public void init() {
        Genre genre = new Genre(null, "Александрова Е.В.");
        genreDao.insert(genre);
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Before test1");
        genreDao.deleteAll();
        System.out.println("Before test2");
    }

}
