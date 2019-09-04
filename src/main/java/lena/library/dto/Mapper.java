package lena.library.dto;

import lena.library.model.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {


    public AuthorDto toAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

    public GenreDto toGenreDto(Genre genre) {
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }

    public BookDto toBookDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setDescription(book.getDescription());
        dto.setWrittenYear(book.getWrittenYear());
        dto.setReaded(book.isReaded());
        return dto;
    }

    public Author fromAuthrorDto(AuthorDto dto) {
        Author author1 = new Author();
        author1.setId(dto.getId());
        author1.setName(dto.getName());
        //чо с книгами?
        return author1;
    }

    public Genre fromGenreDto(GenreDto dto) {
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
//чо со списком книг
        return genre;
    }

    public Book fromBookDto(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setName(dto.getName());
        book.setDescription(dto.getDescription());
        book.setWrittenYear(dto.getWrittenYear());
//прочтено ли, список жанров и авторов
        return book;
    }

    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPassword(user.getPassword());
        dto.setRole(toRoleDto(user.getRole()));
        dto.setEnabled(user.isEnabled());
        return dto;
    }

    public User fromUserDto(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setEnabled(dto.isEnabled());
        user.setRole(fromRoleDto(dto.getRole()));
        return user;
    }

    public RoleDto toRoleDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    public Role fromRoleDto(RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }
}
