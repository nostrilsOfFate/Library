package lena.library.service;

import lena.library.dto.UserDto;
import lena.library.dto.UserRegistrationDto;
import lena.library.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registration);

    User update(UserDto userDto);

    User findByEmail(String email);

    User findById(Integer userId);

    Boolean isUserExistsById(String userId);

    Boolean isUserExistsByEmail(String email);

    List<User> findAll();

    List<User> findAllByIdIn(List<String> ids);

    Boolean delete(Integer userId);



}
