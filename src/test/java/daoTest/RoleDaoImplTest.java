package daoTest;

import lena.library.dao.AuthorDao;
import lena.library.dao.RoleDao;
import lena.library.model.Author;
import lena.library.model.Genre;
import lena.library.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
public class RoleDaoImplTest {

    @Autowired
    private RoleDao roleDao;

    @BeforeEach
    public void init() {
        Role role = new Role( "Александрова Е.В.");
        roleDao.create(role);
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Before test1");
        roleDao.deleteAll();
        System.out.println("Before test2");
    }
    @Test
    public void deleteAllTest() throws DataAccessException {
        roleDao.deleteAll();
        List<Role> actual = roleDao.getAllRoles();
        assertEquals(0, actual.size());
    }
    @Test
    public void findByNameTest() {
        Role role = new Role();
        role.setName("админ");
        Role role1 = roleDao.findByName(role.getName());
        assertEquals(role.getName(), role1.getName());
    }
    @Test
    public void getAllRolesTest() throws DataAccessException {
        List<Role> expected = new ArrayList<>();
        Role role1 = new Role("Пушкин");
        Role role2 = new Role("Кинг");
        Role role3 = new Role("Сноуден");
        expected.add(role1);
        expected.add(role2);
        expected.add(role3);
        roleDao.create(role1);
        roleDao.create(role2);
        roleDao.create(role3);
        List<Role> actual = roleDao.getAllRoles();
        assertEquals(expected.size()+1, actual.size());
    }


}
