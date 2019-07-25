package lena.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"genres", "authors"})
@NoArgsConstructor
@Table(name = "data_genre.books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Column(name = "id")
    @Getter
    private Integer id;


    @Setter
    @Column(name = "name")
    @Getter
    private String name;


    @Setter
    @Column(name = "writtenYear")
    @Getter
    private Integer writtenYear;


    @Setter
    @Column(name = "description")
    @Getter
    private String description;


    @Setter
    @ManyToMany(mappedBy = "data_genre.authors_books")
    @Getter
    private Set<Author> authors;


    @Setter
    @ManyToMany(mappedBy = "data_genre.genres_books")
    @Getter
    private Set<Genre> genres;

    @Column(name = "readed")
    @Getter
    @Setter
    private boolean readed = false;  //прочитано ли, по дефолту НЕТ

    //
//    public Book(Book book) {
//        this(book.getId(), book.getName(), book.getWrittenYear(), book.getDescription(), book.getAuthors(), book.getGenres());
//    }
//
//    public Book(Integer id, String name,Integer writtenYear, String description,Set<Author> authors, Set<Genre> genres) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.authors = authors;
//        this.genres = genres;
//    }
//    public Book(String name, Integer writtenYear, String description, Set<Author> authors, Set<Genre> genres) {
////        this(null, name, writtenYear, description, authors, genres);
////    }
//
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", writtenYear=" + writtenYear +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", readed=" + readed +
                '}';
    }
}
