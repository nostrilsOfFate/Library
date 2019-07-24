package lena.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "books")
public class Author {
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "data_genre.authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )    private List<Book> books;

    public Author(String name) {
        this.id = null;
        this.name = name;
    }


    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
