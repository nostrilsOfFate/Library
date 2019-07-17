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

    //private Integer id;
    //   private String name;
    //   private String description;
    //   private Set<Author> authors;
    //   private Set<Genre> genres;
    //   private Integer writtenYear;

//    void updateBook(String nameOfBook); //обновить книгу
//
//    void deleteDescription();
//    void updateDescription(String newDescription);
//
//    void addAuthor(Author author);
//    void deleteAuthor(Author author);
//    void deleteAllAuthors();
//
//    void updateWrittenYear(Integer newWrittenYear);
//
//    String getName();
//    String getDescription();
//    Set<Author> getAuthors();
//    Set<Genre> getGenre();
//
//    Integer getWrittenYear();
    int insert(Book book); //обновление
    Book getById(int n) throws DataAccessException;
    List<Book> getByName(String name) throws DataAccessException;
    List<Book> getAll();
    int deleteById(int id);
    int deleteByName(String name);

}
