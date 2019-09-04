package lena.library.dto;

import lena.library.model.Author;
import lena.library.model.Genre;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Integer id;

    private String name;

    private Integer writtenYear;
    private String description;


    private boolean readed;

    Set<Author> authors;
    Set<Genre> genres;
}
