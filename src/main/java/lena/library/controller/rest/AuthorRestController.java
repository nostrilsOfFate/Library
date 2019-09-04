package lena.library.controller.rest;

import lena.library.dto.AuthorDto;
import lena.library.dto.Mapper;
import lena.library.model.Author;
import lena.library.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/authors")
public class AuthorRestController {


    private final AuthorService service;



    @Autowired
    public AuthorRestController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        log.info("Get all authors");
        List<Author> authors = service.getAllAuthors();
        List<AuthorDto> dtos = new ArrayList<>();
        authors.forEach(author -> dtos.add(Mapper.toAuthorDto(author)));
        return dtos;
    }
    @GetMapping("/id/{authorId}")
    public AuthorDto findById(@PathVariable Integer authorId) {
        log.info("Find author by id: {}", authorId);
        return Mapper.toAuthorDto(service.getAuthorById(authorId));
    }
    @GetMapping("/name/{authorName}")
    public AuthorDto findByName(@PathVariable String authorName) {
        log.info("Find author by name: {}", authorName);
        return Mapper.toAuthorDto(service.getAuthorByName(authorName));
    }

    @PostMapping
    public AuthorDto create(@RequestBody String authorName) {
        log.info("Create new genre: {}", authorName);
        Author author = service.addAuthor(authorName);
        return author != null ? Mapper.toAuthorDto(author) : null;
    }

    @PutMapping
    public AuthorDto update(@RequestBody AuthorDto dto) {
        log.info("Update author: {}", dto.toString());
        Author genre = service.updateAuthor(dto.getId(),dto.getName());
        return genre != null ? Mapper.toAuthorDto(genre) : null;
    }

}
