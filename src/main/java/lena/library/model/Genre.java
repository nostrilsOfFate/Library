package lena.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(exclude = "books")
@Entity
@Table(name = "data_genre.genres")
public class Genre {
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
    @JoinTable(name = "data_genre.genres_books",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @Getter
    private List<Book> books = new ArrayList<Book>();

    public Genre(String name) {
        this.id = null;
        this.name = name;
    }

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
