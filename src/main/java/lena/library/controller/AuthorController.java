package lena.library.controller;

import lena.library.service.AuthorService;
import lena.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


}
