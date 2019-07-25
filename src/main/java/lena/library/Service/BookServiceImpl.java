//package lena.library.Service;
//
//import lena.library.dao.AuthorDao;
//import lena.library.dao.BookDao;
//import lena.library.dao.GenreDao;
//import lena.library.model.Author;
//import lena.library.model.Book;
//import lena.library.model.Genre;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class BookServiceImpl implements BookService {
//    protected final Log logger = LogFactory.getLog(BookServiceImpl.class);
//    private final BookDao bookDao;
//    private final AuthorDao authorDao;
//        private final GenreDao genreDao;
//
//@Autowired
//    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
//        this.bookDao = bookDao;
//        this.authorDao = authorDao;
//        this.genreDao = genreDao;
//    }
////public Integer addBook(String name, String description, String authorName, String genreName) {
//////        Author author = null;
//////        Genre genre = null;
//////
//////        try {
//////            author = authorDao.getByName(authorName);
//////        } catch (Exception ignored){}
//////        try {
//////            genre = genreDao.getByName(genreName);
//////        } catch (Exception ignored) {}
//////
//////        if (author == null) {
//////            author = new Author(null, authorName);
//////            authorDao.insert(author);
//////        }
//////        if (genre == null) {
//////            genre = new Genre(null, genreName);
//////            genreDao.insert(genre);
//////        }
//////
//////        Book book = new Book(name, description, author, genre);
//////        return bookDao.insert(book);
//////    }
//    @Override
//    public Integer addBook(String nameOfBook, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres) {
//        authors = null;
//        genres = null;
////        try {
////
////        }
//
////        Book book = new Book(nameOfBook, writtenYear, description, authors1,genres1);
////        return bookDao.insert(book);
//        return null;
//    }
//
//    @Override
//    public Integer updateBook(Integer id, String nameOfBook, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres) {
//        Author author = null;
//        Genre genre = null;
//
//     //  try {
////            author = authorDao.getByName(authorName);
////        } catch (Exception ignored){}
////        try {
////            genre = genreDao.getByName(genreName);
////        } catch (Exception ignored) {}
////
////        if (author == null) {
////            author = new Author(null, authorName);
////            authorDao.insert(author);
////        }
////        if (genre == null) {
////            genre = new Genre(null, genreName);
////            genreDao.insert(genre);
//    //   }
////
////        Book book = new Book(id, name, description, author, genre);
////        return bookDao.insert(book);
//   return 1;
//    }
//
//    @Override
//    public Integer deleteBookById(int id) {
//    return bookDao.deleteById(id);
//    }
//
//    @Override //будет ли робить, если у нас могут быть одинаковые названия
//    public Integer deleteBookByName(String name)
//    {
//        return bookDao.deleteByName(name);
//    }
//
//    @Override
//    public Book getBookById(int id) {
//        try {
//            return bookDao.getById(id);
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//
//    @Override // собираем лист
//    public List<Book> getBookByName(String name) {
//    return bookDao.getByName(name);
//    }
//
//    @Override
//    public List<Book> getAllBooks() {
//        return bookDao.getAll();
//    }
//}
//
////
////    @Override
////    public Integer deleteBookById(int id) {
////        return bookDao.deleteById(id);
////    }
////
////    @Override
////    public Integer updateBook(int id,String name, String description, String authorName, String genreName) {
////        Author author = null;
////        Genre genre = null;
////
////        try {
////            author = authorDao.getByName(authorName);
////        } catch (Exception ignored){}
////        try {
////            genre = genreDao.getByName(genreName);
////        } catch (Exception ignored) {}
////
////        if (author == null) {
////            author = new Author(null, authorName);
////            authorDao.insert(author);
////        }
////        if (genre == null) {
////            genre = new Genre(null, genreName);
////            genreDao.insert(genre);
////        }
////
////        Book book = new Book(id, name, description, author, genre);
////        return bookDao.insert(book);
////    }
