package lena.library.service;

import lena.library.dao.RoleDao;
import lena.library.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private  RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role create(String name) {
        Role role = new Role(name);
        return roleDao.create(role);
    }

    @Override
    public Role getByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public Boolean delete(String name) {
        return roleDao.deleteByName(name);
    }

    @Override
    public boolean isExistsByName(String name) {
        return roleDao.existsByName(name);
    }
}
