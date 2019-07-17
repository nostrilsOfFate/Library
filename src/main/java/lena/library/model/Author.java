package lena.library.model;

import lombok.Data;


import java.util.List;

@Data
public class Author {
    private Integer id;
    private String name;
    private List<Book> books;

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
