package lena.library.service;

import lena.library.model.Genre;

import java.util.List;

public interface GenreService {

    Genre addGenre(String name); //добавить жанр

    Boolean deleteGenreById(Integer id);

    Boolean deleteGenreByName(String name);

    Genre updateGenre(Integer id, String newName);

    Genre getGenreById(Integer id);

    Genre getGenreByName(String name);

    List<Genre> getAllGenres();
}
