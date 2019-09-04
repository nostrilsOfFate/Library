package serviceTest;

import lena.library.service.AuthorService;
import lena.library.dao.AuthorDao;
import lena.library.model.Author;
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
public class AuthorServiceImplTest { //extends UnitilsJUnit5
    //@Resource(name="authorServiceImpl")
    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AuthorService authorService;

    @BeforeEach
    public void init() {
        Author author1 = new Author(null, "Александрова Е.В.");
        authorDao.insert(author1);
    }

    @AfterEach
    public void cleanup() {
        authorDao.deleteAll();
    }

    @Test
    public void addAuthorTest() {
        Author author = new Author(null, "Афанасьев П.В.");
        Author actual = authorService.addAuthor(author.getName());
        assertEquals(author.getName(), actual.getName());
    }

    @Test
    public void deleteAuthorByIdTest() {
        int a = authorDao.getAllAuthors().get(0).getId();
        boolean b = authorService.deleteAuthorById(a);
        assertNotNull(b);
        assertTrue(b);
    }

    @Test
    public void deleteAuthorByNameTest() {
        boolean b = authorService.deleteAuthorByName("Александрова Е.В.");
        assertNotNull(b);
        assertTrue(b);
    }

    @Test
    public void updateAuthorTest() {
        Author author = authorDao.getAllAuthors().get(0);
        Author author1 = authorService.updateAuthor(author.getId(), "Иванов И.И.");
        assertNotNull(author1);
        assertEquals("Иванов И.И.", author1.getName());
        assertEquals(author.getBooks(), author1.getBooks());
        assertEquals(author.getId(), author1.getId());
    }

    @Test
    public void getAuthorByIdTest() {
        Author author = authorDao.getAllAuthors().get(0);
        Author author1 = authorService.getAuthorById(author.getId());
        assertNotNull(author1);
        assertEquals(author.getName(), author1.getName());
        assertEquals(author.getId(), author1.getId());
        assertEquals(author.getBooks(), author1.getBooks());
    }

    @Test
    public void getAuthorByNameTest() {
        Author author = authorDao.getAllAuthors().get(0);
        Author author1 = authorService.getAuthorByName(author.getName());
        assertNotNull(author1);
        assertEquals(author.getName(), author1.getName());
        assertEquals(author.getId(), author1.getId());
        assertEquals(author.getBooks(), author1.getBooks());
    }

    @Test
    public void getAllAuthorsTest() {
        List<Author> list = authorDao.getAllAuthors();
        List<Author> list1 = authorService.getAllAuthors();
        assertEquals(list.size(), list1.size());
    }
}

