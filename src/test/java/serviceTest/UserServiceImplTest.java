package serviceTest;

import lena.library.dao.RoleDao;
import lena.library.dao.UserDao;
import lena.library.dto.Mapper;
import lena.library.dto.UserDto;
import lena.library.dto.UserRegistrationDto;
import lena.library.model.Role;
import lena.library.model.User;
import lena.library.service.RoleServiceImpl;
import lena.library.service.UserService;
import lena.library.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
@WebAppConfiguration
class UserServiceImplTest {
    //дао определить в тест конфиге

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;


    @BeforeEach
    public void init() {
        User user = new User("firstName", "secondName",
                "admin@mail.com", "password");
        Role role = new Role("USER");
        roleDao.create(role);
        user.setRole(role);
        userDao.create(user);
    }

    @AfterEach
    public void cleanup() {
        userDao.deleteAll();
    }


    @Test
    void save() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail("user@mail.ru");
        userRegistrationDto.setFirstName("usr1");
        userRegistrationDto.setLastName("user1");
        userRegistrationDto.setPassword("11user");
        User actual = userService.save(userRegistrationDto);
        assertNotNull(actual);
        assertEquals("user@mail.ru", actual.getEmail());
        assertEquals("usr1", actual.getFirstName());
        assertEquals("user1", actual.getLastName());
    }

    @Test
    void update() {
        User user = userDao.getAllUsers().get(0);
        UserDto expected = Mapper.toUserDto(user);
        expected.setLastName("halo");
        expected.setFirstName("halo");
        User actual = userService.update(expected);
        assertNotNull(actual);
        assertNotNull(actual.getEmail());
        assertNotNull(actual.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    void findByEmail() {
        User expected = userDao.getAllUsers().get(0);
        User actual = userService.findByEmail(expected.getEmail());
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    void findById() {
        User expected = userDao.getAllUsers().get(0);
        User actual = userService.findById(expected.getId());
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    void isUserExistsById() {
        User expected = userDao.getAllUsers().get(0);
        Boolean b = userService.isUserExistsById(expected.getId().toString());
        assertTrue(b);
    }

    @Test
    void isUserExistsByEmail() {
        User expected = userDao.getAllUsers().get(0);
        Boolean b = userService.isUserExistsByEmail(expected.getEmail());
        assertTrue(b);
    }

    @Test
    void findAll() {
        User expected = userDao.getAllUsers().get(0);
        List<User> actual = userService.findAll();
        actual.forEach(u -> assertEquals(expected.getId(), u.getId()));
        assertTrue(actual.size() > 0);
    }

    @Test
    void findAllByIdIn() {
        User expected = userDao.getAllUsers().get(0);
        List<String> list = new ArrayList<>();
        list.add(expected.getId().toString());
        List<User> users = userService.findAllByIdIn(list);
        users.forEach(u -> assertEquals(expected.getId(), u.getId()));
        assertEquals(1, list.size());
    }

    @Test
    public void deleteTest() {
        int a = userDao.getAllUsers().get(0).getId();
        boolean b = userService.delete(a);
        assertNotNull(b);
        assertTrue(b);
    }

}

