package lena.library.Service;

import lena.library.model.Book;
import lena.library.model.Genre;

import java.util.List;

public interface GenreService {

    Integer addGenre(String name); //добавить жанр

    Integer deleteGenreById(Integer id);

    Integer deleteGenreByName(String name);

    Integer updateGenre(Integer id, String newName);

    Genre getGenreById(Integer id);

    Genre getGenreByName(String name);

    List<Genre> getAllGenres();
}
