package lena.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@EqualsAndHashCode(exclude = "books")
@Entity
public class Genre {
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
    @JoinTable(name = "data_genre.genres_books",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

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
