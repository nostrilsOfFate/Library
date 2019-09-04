package daoTest;
import lena.library.dao.BookDao;
import lena.library.model.Author;
import lena.library.model.Book;
import lena.library.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void insertTest() {
        Book expected = new Book(null, "Вася Пупкин", 2019, "Эпичные приключения Васи", false);
        Book actual = bookDao.insert(expected);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getWrittenYear(), actual.getWrittenYear());
        assertEquals(expected.isReaded(), actual.isReaded());
    }

    @Test
    public void updateTest() {
        Book book = bookDao.getAllBooks().get(0);
        book.setName("Ananas. Instruction.");
        book.setWrittenYear(2001);
        book.setDescription("Инструкция по выращиванию ананаса");
        book.setReaded(false);

        bookDao.update(book);
        assertEquals("Ananas. Instruction.", book.getName());
        assertEquals("Инструкция по выращиванию ананаса", book.getDescription());
        assertEquals(2001, book.getWrittenYear().intValue());
        assertEquals(false, book.isReaded());
    }

    @Test
    public void getByIdTest() throws DataAccessException { //переписать
        Book book = bookDao.getAllBooks().get(0);
        Book book1 = bookDao.getById(book.getId());
        assertEquals(book.getName(), book1.getName());
    }

    @Test
    public void getByNameTest() throws DataAccessException { //переписать
        Book book = new Book(null, "No Name",  2000,"", false);
        Book book1 = new Book(null, "No Name", 2001, "Другое сочинение", true);
        Book book2 = new Book(null, "No Name1", 2001, "Другое сочинение", true);
        bookDao.insert(book);
        bookDao.insert(book1);
        bookDao.insert(book2);
        List<Book> books = bookDao.getByName(book.getName());
        assertNotNull(books);
        assertEquals(2, books.size());
    }

    @Test
    public void deleteByIdTest() {
        Book book = bookDao.getAllBooks().get(0);
        bookDao.deleteById(book.getId());
        Book bookAfterDeleting = bookDao.getById(book.getId());
        assertNull(bookAfterDeleting);
    }

    @Test
    public void deleteByNameTest() {
        Book book = new Book(null, "No Name", 2000, "", false);
        Book book1 = new Book(null, "No Name", 2001, "Другое сочинение", true);
        Book book2 = new Book(null, "No Name1", 2001, "Другое сочинение", true);
        bookDao.insert(book);
        bookDao.insert(book1);
        bookDao.insert(book2);
        bookDao.deleteByName("No Name");
        List<Book> books = bookDao.getByName("No Name");
        assertNotNull(books);
        assertEquals(0, books.size());
    }


    @Test
    public void getAllBooks() throws DataAccessException {
        List<Book> expected = new ArrayList<>();
        Book book1 = new Book(null, "No Name", 2000, "", false);
        Book book2 = new Book(null, "No Name1", 2001, "1", false);
        Book book3 = new Book(null, "No Name2", 2002, "2", false);
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        bookDao.insert(book1);
        bookDao.insert(book2);
        bookDao.insert(book3);
        List<Book> actual = bookDao.getAllBooks();
        assertNotNull(actual);
        assertEquals(expected.size()+1, actual.size());
    }

    @Test
    public void deleteAllTest() throws DataAccessException {
        bookDao.deleteAll();
        List<Book> actual = bookDao.getAllBooks();
        assertEquals(0, actual.size());
    }

    @BeforeEach
    public void init() {
        Set<Author> authors = new HashSet<>();
        Author author1 = new Author(null, "Иванов И.И.");
        Set<Genre> genres = new HashSet<>();
        Genre genre = new Genre(null, "Мемуары");
        Book book1 = new Book(null, "Эпичные приключения Лены", 1950, "Приключения автора программы", false);
        authors.add(author1);
        genres.add(genre);
        book1.setAuthors(authors);
        book1.setGenres(genres);
        bookDao.insert(book1);
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Before test1");
        bookDao.deleteAll();
        System.out.println("Before test2");
    }
}
