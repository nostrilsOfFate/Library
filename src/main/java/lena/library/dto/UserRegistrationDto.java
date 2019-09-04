package lena.library.dto;

import lena.library.validation.FieldMatch;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
@Getter
@Setter
@ToString
public class UserRegistrationDto {

    private String firstName;

    private String lastName;

    private String password;

    private String confirmPassword;

    private String email;

    private String confirmEmail;

   //изначально должно быть тру
    private Boolean terms;

}
