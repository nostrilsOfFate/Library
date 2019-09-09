package daoTest;

import lena.library.dao.UserDao;
import lena.library.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:testConfig.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @BeforeEach
    public void init() {
        User user = new User("Афанасий", "Афанасьев", "afanasiev@gmail.com", "12345");
        userDao.create(user);
    }

    @AfterEach
    public void cleanup() {
        System.out.println("Before test1");
        userDao.deleteAll();
        System.out.println("Before test2");
    }

    // User create(User user);
    @Test
    public void createTest() {
        User user = new User("Николай", "Николаев", "nikolaev@gmail.com", "12345");
        User user1 = userDao.create(user);
        assertNotNull(user1);
        assertEquals(user.getId(), user1.getId());
        assertEquals(user.getEmail(), user1.getEmail());
        assertEquals(user.getFirstName(), user1.getFirstName());
        assertEquals(user.getLastName(), user1.getLastName());
        assertEquals(user.getPassword(), user1.getPassword());
    }

    @Test
    public void findByEmailTest() { //    User findByEmail(String email);
        User user = userDao.getAllUsers().get(0);
        User user1 = userDao.findByEmail(user.getEmail());
        assertEquals(user.getEmail(), user1.getEmail());
    }

    @Test
    public void findByIdTest() {//    User findById(Integer userId);
        User user = userDao.getAllUsers().get(0);
        User user1 = userDao.findById(user.getId());
        assertEquals(user.getId(), user1.getId());
    }

    @Test
    public void existsByIdTest() { //    boolean existsById(String userId);
        User user = userDao.getAllUsers().get(0);
        boolean b = userDao.existsById(user.getId());
        assertTrue(b);
    }

    @Test
    public void existsByEmailTest() { //    boolean existsByEmail(String email);
        User user = userDao.getAllUsers().get(0);
        boolean b = userDao.existsByEmail(user.getEmail());
        assertTrue(b);
    }

    @Test
    public void findAllByIdInTest() {//    List<User> findAllByIdIn(List<String> ids);
        User user = userDao.getAllUsers().get(0);
        List<Integer> list = new ArrayList<>();
        list.add(user.getId());
        List<User> actual = userDao.findAllByIdIn(list);
        assertEquals(1, actual.size());
    }

    @Test
    public void deleteAllTest() { //    void deleteAll();
        userDao.deleteAll();
        List<User> actual = userDao.getAllUsers();
        assertEquals(0, actual.size());
    }

    @Test
    public void deleteByIdTest() { //    Boolean deleteById(Integer id);
        User user = userDao.getAllUsers().get(0);
        userDao.deleteById(user.getId());
        boolean b = userDao.deleteById(user.getId());
        assertTrue(b);
    }

    @Test
    public void deleteByEmailTest() {//    Boolean deleteByName(String name);
        User user = userDao.getAllUsers().get(0);
        userDao.deleteByEmail(user.getEmail());
        boolean b = userDao.existsByEmail(user.getEmail());
        assertTrue(b);

    }

    @Test
    public void getAllUsersTest() {  //    List<User> getAllUsers();
        List<User> expected = new ArrayList<>();
        User user1 = new User("Иван", "Иванов", "ivanov@gmail.com", "12345");
        expected.add(user1);
        userDao.create(user1);
        List<User> actual = userDao.getAllUsers();
        assertEquals(expected.size() + 1, actual.size());
    }
}
