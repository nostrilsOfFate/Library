package lena.library.dao;
import lena.library.model.Author;
import lena.library.model.Book;
import lena.library.model.Genre;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Set;

public interface BookDao { //главная сущность, может в разу больше мелкихб к ней
    // можно добавлять авторов, где приходит сущность автора с уже добавленной книгой
    //мы перезаписываем эту сущность

    int insert(Book book); //обновление
    void update(Book book);
    Book getById(int id) throws DataAccessException;
    List<Book> getByName(String name) throws DataAccessException;
    List<Book> getAllBooks();
    void deleteById(int id);
    void deleteByName(String name);

}
