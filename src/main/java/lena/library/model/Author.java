package lena.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "books")
@Table(name = "data_genre.authors")
public class Author {
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "data_genre.authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @Getter
    private List<Book> books = new ArrayList<Book>();


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
