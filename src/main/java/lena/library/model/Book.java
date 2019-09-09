package lena.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "library.books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @Column(name = "writtenYear")
    private Integer writtenYear;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "library.authors_books")
    private Set<Author> authors;

    @ManyToMany(mappedBy = "library.genres_books")
    private Set<Genre> genres;

    @Column(name = "readed")
    private boolean readed;  //прочитано ли, по дефолту НЕТ

    public boolean isReaded() {
        return readed;
    }

    public Book(Book book) {
        this(book.getId(), book.getName(), book.getWrittenYear(), book.getDescription(), book.isReaded());
    }

    public Book(Integer id, String name, Integer writtenYear, String description, boolean readed) {
        this.id = id;
        this.name = name;
        this.writtenYear = writtenYear;
        this.description = description;
        this.readed = readed;
    }

    public boolean isNew() {
        return this.id == null;
    }

}
