package lena.library.dao;

import lena.library.model.Role;
import lena.library.model.User;

import java.util.List;

public interface UserDao {

    User create(User user);

    User findByEmail(String email);

    User findById(Integer userId);

    boolean existsById(String userId);

    boolean existsByEmail(String email);

    List<User> findAllByIdIn(List<String> ids);

    void deleteAll();

    Boolean deleteById(Integer id);

    Boolean deleteByName(String name);

    List<User> getAllUsers();
}

