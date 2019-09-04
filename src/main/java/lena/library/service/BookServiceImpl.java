package lena.library.service;

import lena.library.dao.AuthorDao;
import lena.library.dao.BookDao;
import lena.library.dao.GenreDao;
import lena.library.model.Author;
import lena.library.model.Book;
import lena.library.model.Genre;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    protected final Log logger = LogFactory.getLog(BookServiceImpl.class);
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Book addBook(String nameOfBook, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres) {
        Book book = new Book(null, nameOfBook, writtenYear, description, false);
        book.setGenres(genres);
        book.setAuthors(authors);
        return bookDao.insert(book);
    }

    @Override
    public Book updateBook(Integer id, String nameOfBook, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres) {
        Book book = bookDao.getById(id);
        book.setName(nameOfBook);
        book.setWrittenYear(writtenYear);
        book.setDescription(description);
        book.setGenres(genres);
        book.setAuthors(authors);
        return bookDao.update(book);
    }

    @Override
    public Boolean deleteBookById(int id) {
        return bookDao.deleteById(id);
    }

    @Override //будет ли робить, если у нас могут быть одинаковые названия
    public Boolean deleteBookByName(String name) {
        return bookDao.deleteByName(name);
    }

    @Override
    public Book getBookById(int id) {
        try {
            return bookDao.getById(id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override // собираем лист
    public List<Book> getBooksByName(String name) {
        try {
            return bookDao.getByName(name);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        try {
            return bookDao.getAllBooks();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

