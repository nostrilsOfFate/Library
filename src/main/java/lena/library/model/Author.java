package lena.library.model;

import lena.library.dto.AuthorDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "data_genre.authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "data_genre.authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

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

}
