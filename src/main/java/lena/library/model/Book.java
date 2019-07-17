package lena.library.model;

import lombok.Data;

import java.util.Set;

@Data
public class Book {
    private Integer id;
    private String name;
    private Integer writtenYear;
    private String description;
    private Set<Author> authors;
    private Set<Genre> genres;

    public Book(Book book) {
        this(book.getId(), book.getName(), book.getWrittenYear(), book.getDescription(), book.getAuthors(), book.getGenres());
    }

    public Book(Integer id, String name,Integer writtenYear, String description,Set<Author> authors, Set<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authors = authors;
        this.genres = genres;
    }
    public Book(String name, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres) {
        this(null, name, writtenYear, description, authors, genres);
    }

    public boolean isNew() {
        return this.id == null;
    }
}
