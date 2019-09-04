package serviceTest;

import lena.library.service.GenreService;
import lena.library.dao.GenreDao;
import lena.library.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
@WebAppConfiguration
public class GenreServiceImplTest {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private GenreService genreService;

    @BeforeEach
    public void init() {
        Genre genre1 = new Genre(null, "романтика");
        genreDao.insert(genre1);
    }

    @AfterEach
    public void cleanup() {
        genreDao.deleteAll();
    }

    @Test
    public void addGenreTest() {
        // Genre addGenre(String name); //добавить жанр
        Genre genre = new Genre(null, "Словари");
        Genre genre1 = genreService.addGenre(genre.getName());
        assertEquals(genre.getName(), genre1.getName());
    }


    @Test
    public void deleteGenreByIdTest() { //
        //    Boolean deleteGenreById(Integer id);
        int a = genreDao.getAllGenres().get(0).getId();
        boolean b = genreService.deleteGenreById(a);
        assertNotNull(b);
        assertTrue(b);
    }

    @Test
    public void deleteGenreByNameTest() {
        //    Boolean deleteGenreByName(String name);
        boolean b = genreService.deleteGenreByName("романтика");
        assertNotNull(b);
        assertTrue(b);
    }

    @Test
    public void updateGenreTest() {
        //    Genre updateGenre(Integer id, String newName);
        Genre genre = genreDao.getAllGenres().get(0);
        Genre genre1 = genreService.updateGenre(genre.getId(), "ужасы");
        assertNotNull(genre1);
        assertEquals("ужасы", genre1.getName());
        assertEquals(genre.getId(), genre1.getId());
        assertEquals(genre.getBooks(), genre1.getBooks());
    }

    @Test
    public void getGenreByIdTest() {
        //    Genre getGenreById(Integer id);
        Genre genre = genreDao.getAllGenres().get(0);
        Genre genre1 = genreService.getGenreById(genre.getId());
        assertNotNull(genre1);
        assertEquals(genre.getName(), genre1.getName());
        assertEquals(genre.getId(), genre1.getId());
        assertEquals(genre.getBooks(), genre1.getBooks());
    }

    @Test
    public void getGenreByNameTest() {
        //    Genre getGenreByName(String name);
        Genre genre = genreDao.getAllGenres().get(0);
        Genre genre1 = genreService.getGenreByName(genre.getName());
        assertNotNull(genre1);
        assertEquals(genre.getName(), genre1.getName());
        assertEquals(genre.getId(), genre1.getId());
        assertEquals(genre.getBooks(), genre1.getBooks());
    }

    @Test
    public void getAllGenresTest() {
        //    List<Genre> getAllGenres();
        List<Genre> list = genreDao.getAllGenres();
        List<Genre> list1 = genreService.getAllGenres();
        assertEquals(list.size(), list1.size());
    }
}
