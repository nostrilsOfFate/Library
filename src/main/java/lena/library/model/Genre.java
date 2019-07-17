package lena.library.model;

import lombok.Data;

import java.util.List;

@Data
public class Genre {
    private Integer id;
    private String name;
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
}
