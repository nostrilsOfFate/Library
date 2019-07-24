package lena.library.Service;

import lena.library.model.Author;
import lena.library.model.Book;

import java.util.List;

public interface AuthorService {
    // private Integer id;
    //    private String name;
    //    private List<Book> books;

    Author addAuthor(String name);

    Integer deleteAuthorById(Integer id);

    Integer deleteAuthorByName(String name);

    Integer updateAuthor(Integer id, String newName); //можно и по имени

    Author getAuthorById(Integer id);

    Author getAuthorByName(String name);

    List<Author> getAllAuthors();

}
