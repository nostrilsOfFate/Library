package lena.library.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private RoleDto role;

    private boolean enabled = true;
}
