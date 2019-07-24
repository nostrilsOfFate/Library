//package lena.library.Service;
//
//import lena.library.dao.GenreDao;
//import lena.library.model.Genre;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GenreServiceImpl implements GenreService {
//    private final GenreDao genreDao;
//
//    @Autowired
//    public GenreServiceImpl(GenreDao genreDao) {
//        this.genreDao = genreDao;
//    }
//
//    @Override
//    public Integer addGenre(String name) {
//        Genre genre = new Genre(null, name);
//        return genreDao.insert(genre);
//    }
//
//    @Override
//    public Integer deleteGenreById(Integer id) {
//        return genreDao.deleteById(id);
//    }
//
//    @Override
//    public Integer deleteGenreByName(String name) {
//        return deleteGenreByName(name);
//    }
//
//    @Override
//    public Integer updateGenre(Integer id, String newName) {
//        Genre genre = new Genre(id, newName);
//        return genreDao.insert(genre);
//    }
//
//    @Override
//    public Genre getGenreById(Integer id) {
//        try {
//            return genreDao.getById(id);
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public Genre getGenreByName(String name) {
//
//        try {
//            return genreDao.getByName(name);
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public List<Genre> getAllGenres() {
//        return genreDao.getAll();
//    }
//}
