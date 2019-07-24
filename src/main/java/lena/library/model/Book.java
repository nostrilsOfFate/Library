package lena.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"genres", "authors"})
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    @Getter
    @Setter
   private Integer id;
    @Getter
    @Setter
   private String name;
    @Getter
    @Setter
   private Integer writtenYear;
    @Getter
    @Setter
   private String description;
    @Getter
    @Setter
   @ManyToMany(mappedBy = "data_genre.authors_books")
   private Set<Author> authors;
    @Getter
    @Setter
    @ManyToMany(mappedBy = "data_genre.genres_books")
    private Set<Genre> genres;
    private boolean readed;  //прочитано ли, по дефолту НЕТ
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
