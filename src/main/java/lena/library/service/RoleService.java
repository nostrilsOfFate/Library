package lena.library.service;

import lena.library.model.Role;

public interface RoleService {

    Role create(String name);

    Role getByName(String name);

    Boolean delete(String name);

    boolean isExistsByName(String name);
}
