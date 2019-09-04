package lena.library.service;

import lena.library.dao.GenreDao;
import lena.library.model.Genre;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class GenreServiceImpl implements GenreService {
    protected final Log logger = LogFactory.getLog(GenreServiceImpl.class);

    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre addGenre(String name) {
        Genre genre = new Genre(null, name);
        return genreDao.insert(genre);
    }

    @Override
    public Boolean deleteGenreById(Integer id) {
        return genreDao.deleteById(id);
    }

    @Override
    public Boolean deleteGenreByName(String name) {
        return genreDao.deleteByName(name);
    }

    @Override
    public Genre updateGenre(Integer id, String newName) {
        //?????? возможен косяк
        // Author author = authorDao.getById(id);
        //        author.setId(id);
        //        author.setName(newName);
        //        author.setBooks(author.getBooks());
        //        return authorDao.insert(author);
        Genre genre = new Genre(id, newName);
        return genreDao.update(genre);
    }

    @Override
    public Genre getGenreById(Integer id) {
        try {
            return genreDao.getById(id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Genre getGenreByName(String name) {
        try {
            return genreDao.getByName(name);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> getAllGenres() {
        try {
            return genreDao.getAllGenres();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
