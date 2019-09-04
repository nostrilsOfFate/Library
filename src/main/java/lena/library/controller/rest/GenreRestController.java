package lena.library.controller.rest;

import lena.library.dto.GenreDto;
import lena.library.dto.Mapper;
import lena.library.model.Genre;
import lena.library.service.GenreService;
import lena.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/genres")
public class GenreRestController {

    private final GenreService service;

    @Autowired
    public GenreRestController(GenreService service) {
        this.service = service;
    }

    @GetMapping
    public List<GenreDto> getAll() {
        log.info("Get all genres");
        List<Genre> genres = service.getAllGenres();
        List<GenreDto> dtos = new ArrayList<>();
        genres.forEach(genre -> dtos.add(Mapper.toGenreDto(genre)));
        return dtos;
    }

    @GetMapping("/id/{genreId}")
    public GenreDto findById(@PathVariable Integer genreId) {
        log.info("Find genre by id: {}", genreId);
        return Mapper.toGenreDto(service.getGenreById(genreId));
    }

    @GetMapping("/name/{genreName}")
    public GenreDto findByName(@PathVariable String genreName) {
        log.info("Find genre by name: {}", genreName);
        return Mapper.toGenreDto(service.getGenreByName(genreName));
    }

    @DeleteMapping("/{genreId}")
    public void deleteById(@PathVariable Integer genreId) {
        log.info("Delete genre by id: {}", genreId);
        service.deleteGenreById(genreId);
    }

    @DeleteMapping("/{genres}")
    public void deleteByName(@PathVariable String genreName) {
        log.info("Delete genre by name: {}", genreName);
        service.deleteGenreByName(genreName);
    }

    @PostMapping
    public GenreDto create(@RequestBody String genreName) {
        log.info("Create new genre: {}", genreName);
        Genre genre = service.addGenre(genreName);
        return genre != null ? Mapper.toGenreDto(genre) : null;
    }

    @PutMapping
    public GenreDto update(@RequestBody GenreDto dto) {
        log.info("Update genre: {}", dto.toString());
        Genre genre = service.updateGenre(dto.getId(),dto.getName());
        return genre != null ? Mapper.toGenreDto(genre) : null;
    }


}
