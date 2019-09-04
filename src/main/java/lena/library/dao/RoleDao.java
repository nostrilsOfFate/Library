package lena.library.dao;

import lena.library.model.Role;

import java.util.List;

public interface RoleDao {

    Role create(Role role);

    Role findByName(String name);

    List<Role> findAllByNameIn(List<String> names);

    Boolean deleteByName(String name);

    Boolean existsByName(String name);

    void deleteAll();

    List<Role> getAllRoles();
}

