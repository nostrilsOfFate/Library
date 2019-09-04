package lena.library.controller.rest;

import lena.library.dto.BookDto;
import lena.library.dto.Mapper;
import lena.library.model.Book;
import lena.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/books")
public class BookRestController {

    private final BookService service;


    @Autowired
    public BookRestController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookDto> getAll() {
        log.info("Get all books");
        List<Book> books = service.getAllBooks();
        List<BookDto> dtos = new ArrayList<>();
        books.forEach(book -> dtos.add(Mapper.toBookDto(book)));
        return dtos;
    }

    @GetMapping("/id/{bookId}")
    public BookDto findById(@PathVariable Integer bookId) {
        log.info("Find book by id: {}", bookId);
        return Mapper.toBookDto(service.getBookById(bookId));
    }

    @GetMapping("/name/{bookName}") //возможен косяк вывода списка
    public List<BookDto> findByName(@PathVariable String bookName) {
        log.info("Find books by name: {}", bookName);
        List<Book> books = service.getBooksByName(bookName);
        List<BookDto> dtos = new ArrayList<>();
        books.forEach(book -> dtos.add(Mapper.toBookDto(book)));
        return dtos;
    }

    @DeleteMapping("/{bookId}")
    public void deleteById(@PathVariable Integer bookId) {
        log.info("Delete book by id: {}", bookId);
        service.deleteBookById(bookId);
    }

    @DeleteMapping("/{bookName}")
    public void deleteByName(@PathVariable String bookName) {
        log.info("Delete book by name: {}", bookName);
        service.deleteBookByName(bookName);
    }

    @PostMapping
    public BookDto create(@RequestBody BookDto dto) {
        log.info("Create new book: {}", dto.toString());
        Book book = service.addBook(dto.getName(), dto.getWrittenYear(),
                dto.getDescription(), dto.getAuthors(), dto.getGenres());
        return book != null ? Mapper.toBookDto(book) : null;
    }

    @PutMapping
    public BookDto update(@RequestBody BookDto dto) {
        log.info("Update book: {}", dto.toString());
        Book book = service.updateBook(dto.getId(),dto.getName(),dto.getWrittenYear(),
                dto.getDescription(),dto.getAuthors(),dto.getGenres());
        return book != null ? Mapper.toBookDto(book) : null;
    }

}
