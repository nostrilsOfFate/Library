package lena.library.service;

import lena.library.model.Author;

import java.util.List;

public interface AuthorService {

    Author addAuthor(String name);

    Boolean deleteAuthorById(Integer id);

    Boolean deleteAuthorByName(String name);

    Author updateAuthor(Integer id, String newName);

    Author getAuthorById(Integer id);

    Author getAuthorByName(String name);

    List<Author> getAllAuthors();

  // Integer addAuthor(String name);
    //
    //    Integer deleteAuthorById(int id);
    //
    //    Integer deleteAuthorByName(String name);
    //
    //    Integer updateAuthor(int id, String name);
    //
    //    Author getAuthorByName(String name);
    //
    //    Author getAuthorById(int id);
    //
    //    List<Author> getAllAutors();
}
