package serviceTest;

import lena.library.service.BookService;
import lena.library.dao.AuthorDao;
import lena.library.dao.BookDao;
import lena.library.dao.GenreDao;
import lena.library.model.Author;
import lena.library.model.Book;
import lena.library.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
@WebAppConfiguration
public class BookServiceImplTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;

    @BeforeEach
    public void init() {
        Set<Author> authors = new HashSet<>();
        Author author1 = new Author(null, "Иванов И.И.");
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(null, "Мемуары");
        Book book1 = new Book(null, "Эпичные приключения Лены", 1950, "Приключения автора программы", false);
        authorDao.insert(author1);
        authors.add(author1);
        genreDao.insert(genre);
        genres.add(genre);

        book1.setAuthors(authors);
        book1.setGenres(genres);
        bookDao.insert(book1);

    }

    @AfterEach
    public void cleanup() {
        bookDao.deleteAll();
    }

    @Test
    public void addBookTest() {
        Book book = new Book(null, "Го́рдость и предубежде́ние", 1813, "Роман нравов", true);

        Author author = new Author(null, " Джейн Остин");
        authorDao.insert(author);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        book.setAuthors(authors);

        Genre genre = new Genre(null, "Роман");
        genreDao.insert(genre);
        Set<Genre> genres = new HashSet<>();
        genres.add(genre);
        book.setGenres(genres);

        Book book1 = bookService.addBook(book.getName(), book.getWrittenYear(), book.getDescription(), book.getAuthors(), book.getGenres());
        assertEquals(book.getName(), book1.getName());
        assertEquals(book.getWrittenYear(), book1.getWrittenYear());
        assertEquals(book.getDescription(), book1.getDescription());
        assertEquals(book.getGenres().size(), book1.getGenres().size());
        assertEquals(book.getAuthors().size(), book1.getAuthors().size());
    }

    @Test
    public void updateBookTest() {//    Book updateBook(Integer id, String nameOfBook, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres);
        Set<Author> authors = new HashSet<>();
        Author author1 = new Author(null, "Иванов И.И.");
        authors.add(author1);
        authorDao.insert(author1);
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(null, "Мемуары");
        Genre genre1 = new Genre(null, "Приключения");
        genres.add(genre);
        genres.add(genre1);
        genreDao.insert(genre);
        genreDao.insert(genre1);
        Book book = bookDao.getAllBooks().get(0);
        Book book1 = bookService.updateBook(book.getId(), "Жизнь", 1999, "Текст", authors, genres);
        assertNotNull(book1);
        assertEquals("Жизнь", book1.getName());
        assertNotNull(book1.getId());
        assertEquals(1999, book1.getWrittenYear());
        assertEquals("Текст", book1.getDescription());
        assertEquals(1, book1.getGenres().size());
        assertEquals(1, book1.getAuthors().size());
    }

    @Test
    public void deleteBookByIdTest() {//    Boolean deleteBookById(int id);
        int a = bookDao.getAllBooks().get(0).getId();
        boolean b = bookService.deleteBookById(a);
        assertNotNull(b);
        assertTrue(b);
    }

    @Test
    public void deleteBookByNameTest() {//    Boolean deleteBookByName(String name);
        boolean b = bookService.deleteBookByName("Эпичные приключения Лены");
        assertNotNull(b);
        assertTrue(b);
    }

    @Test
    public void getBookByIdTest() {//    Book getBookById(int id);
        Book book = bookDao.getAllBooks().get(0);
        Book book1 = bookService.getBookById(book.getId());
        assertNotNull(book1);
        assertEquals(book.getName(), book1.getName());
        assertEquals(book.getId(), book1.getId());
        assertEquals(book.getWrittenYear(), book1.getWrittenYear());
        assertEquals(book.getDescription(), book1.getDescription());
        assertEquals(book.getAuthors(), book1.getAuthors());
        assertEquals(book.getGenres(), book1.getGenres());
    }

    @Test
    public void getBooksByNameTest() {//    List<Book> getBooksByName(String name);
        List<Book> book = bookDao.getByName("Эпичные приключения Лены");
        List<Book> book1 = bookService.getBooksByName("Эпичные приключения Лены");
        assertNotNull(book1);
        assertEquals(book.size(), book1.size());
        assertEquals(book.get(0).getName(), book1.get(0).getName());
        assertEquals(book.get(0).getDescription(), book1.get(0).getDescription());
        assertEquals(book.get(0).getWrittenYear(), book1.get(0).getWrittenYear());
        assertEquals(book.get(0).getAuthors(), book1.get(0).getAuthors());
    }

    @Test
    public void getAllBooksTest() {//    List<Book> getAllBooks();
        List<Book> list = bookDao.getAllBooks();
        List<Book> list1 = bookService.getAllBooks();
        assertEquals(list.size(), list1.size());
    }
}
