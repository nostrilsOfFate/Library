package lena.library.controller;

import lena.library.model.Genre;
import lena.library.service.BookService;
import lena.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;

public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
}
