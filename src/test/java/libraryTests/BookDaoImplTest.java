package libraryTests;
import lena.library.dao.AuthorDao;
import lena.library.dao.BookDao;
import lena.library.model.Author;
import lena.library.model.Book;
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

public class BookDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Qualifier(value = "jdbcTemplateBookDaoTest")
    @Autowired
    private BookDao bookDao;

    @Test
    public void insertTest() {
        Book expected = new Book(null, "Вася Пупкин", "Эпичные приключения Васи", 2019, false);
        Book actual = bookDao.insert(expected);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void updateTest() {
        bookDao.deleteAll();
        Book book = new Book(null, "No Name", "", 2000, false);
        bookDao.insert(book);
        book.setName("Ananas");
        book.setDescription("Инструкция по выращиванию ананаса");
        book.setWrittenYear(2001);
        book.setReaded(false);

        bookDao.update(book);
        assertEquals("Ananas", book.getName());
    }

    @Test
    public void deleteByIdTest() {
        Book book = new Book("test book");
        // Сохраняю тестовый объект в базе данных
        Book bookToDelete = bookDao.insert(book);
        // Вытаскиваю тестовый объект из базы данных
        // Удаляю тестовый объект из базы данных
        bookDao.deleteById(bookToDelete.getId());
        // Ищу удаленный объект в базе данных
        Book bookAfterDeleting = bookDao.getById(bookToDelete.getId());
        // сравнение вытащенного объекта после удаления из базы данных на null
        assertNull(bookAfterDeleting);
    }

    @Test
    public void deleteByNameTest() {
        Book book = new Book("test book");
        // Сохраняю тестовый объект в базе данных
        Book bookToDelete = bookDao.insert(book);
        // Вытаскиваю тестовый объект из базы данных
        // Удаляю тестовый объект из базы данных
       bookDao.deleteByName(bookToDelete.getName());
        // Ищу удаленный объект в базе данных
       Book bookAfterDeleting = bookDao.getByName(bookToDelete.getName());
        // сравнение вытащенного объекта после удаления из базы данных на null
        assertNull(bookAfterDeleting);
    }

    @Test
    public void getByIdTest() throws DataAccessException { //переписать
        bookDao.deleteAll();
       Book book = new Book("No Name"); // создала автора
       bookDao.insert(book);// созранила его и вернула
        //у возвращенного автора взять ид и испоьльзовать его для поиска по ид
        Book book1 = bookDao.getById(book.getId());
        assertEquals(book.getName(), book1.getName());
    }

    @Test
    public void getByNameTest() throws DataAccessException { //переписать
        bookDao.deleteAll();
        Book book = new Book();
        book.setName("No Name");
        bookDao.insert(book);
        Book book1 = bookDao.getByName(book.getName());
        assertEquals(book.getName(), book1.getName());
    }

    @Test
    public void getAllBooks() throws DataAccessException {
        bookDao.deleteAll();
        List<Book> expected = new ArrayList<>();
        Book book1 = new Book("Пушкин");
        Book book2 = new Book("Кинг");
        Book book3 = new Book("Сноуден");
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        bookDao.insert(book1);
        bookDao.insert(book2);
        bookDao.insert(book3);
        List<Book> actual = bookDao.getAllBooks();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void deleteAllTest() throws DataAccessException {
        //пустой лист
        List<Book> expected = new ArrayList<>();
        //удаляем все строки в дао
        bookDao.deleteAll();
        // проверяем длинну листа
        List<Book> actual = bookDao.getAllBooks();
        //сравниваю значение
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void createNewBaseTest() throws DataAccessException {
        List<Book> expected = new ArrayList<>();
        bookDao.createNewBase();
        List<Book> actual = bookDao.getAllBooks();
        assertEquals(expected.size(), actual.size());
    }
}
