package lena.library.service;

import lena.library.model.Author;
import lena.library.model.Book;
import lena.library.model.Genre;

import java.util.List;
import java.util.Set;

public interface BookService {
    // private Integer id;
    //   private String name;
    //   private String description; // описание
    //   private Set<Author> authors;
    //   private Set<Genre> genres;
    //   private Integer writtenYear;

    Book addBook(String nameOfBook, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres); //обновить книгу

    Book updateBook(Integer id, String nameOfBook, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres);

    Boolean deleteBookById(int id);

    Boolean deleteBookByName(String name);

    Book getBookById(int id);

    List<Book> getBooksByName(String name);

    List<Book> getAllBooks();
}
