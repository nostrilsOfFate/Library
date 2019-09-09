package lena.library.dao;

import lena.library.model.Role;
import lena.library.model.User;

import java.util.List;

public interface UserDao {

    User create(User user);

    User findByEmail(String email);

    User findById(Integer userId);

    boolean existsById(Integer userId);

    boolean existsByEmail(String email);

    List<User> findAllByIdIn(List<Integer> ids);

    void deleteAll();

    Boolean deleteById(Integer id);

    Boolean deleteByEmail(String email);
    List<User> getAllUsers();
}

