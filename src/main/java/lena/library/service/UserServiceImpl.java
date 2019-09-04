package lena.library.service;

import lena.library.dao.UserDao;
import lena.library.dto.Mapper;
import lena.library.dto.UserDto;
import lena.library.dto.UserRegistrationDto;
import lena.library.exceptions.RoleDoesntExistsException;
import lena.library.exceptions.UserDoesNotExistsException;
import lena.library.model.Role;
import lena.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService,
                           @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(UserRegistrationDto registration) {

        Role role = roleService.getByName("ROLE_USER");
        if (role == null) {
            role = new Role("ROLE_USER");
            roleService.create(role.getName());
        }

        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        user.setRole(role);
        return userDao.create(user);
    }

    @Override
    public User update(UserDto userDto) {
        User user = userDao.findByEmail(userDto.getEmail());
        if (user == null) {
            throw new UserDoesNotExistsException(
                    "User with id: " + userDto.getId() + " doesn't exist.");
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.isEnabled());

        String roleName = userDto.getRole().getName();
        Role role;
        if (roleService.isExistsByName(roleName)) {
            role = roleService.getByName(roleName);
        } else {
            throw new RoleDoesntExistsException(roleName);
        }
        user.setRole(role);
        return userDao.create(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findById(Integer userId) {
        return userDao.findById(userId);
    }

    @Override
    public Boolean isUserExistsById(String userId) {
        if (!userDao.existsById(userId)) {
            throw new UserDoesNotExistsException("User with ID <" + userId + "> doesn't exist.");
        }
        return true;
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        if (!userDao.existsByEmail(email)) {
            throw new UserDoesNotExistsException("User with email <" + email + "> doesn't exist.");
        }
        return true;
    }

    @Override
    public List<User> findAll() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> findAllByIdIn(List<String> ids) {
        return userDao.findAllByIdIn(ids);
    }

    @Override
    public Boolean delete(Integer userId) {
        return userDao.deleteById(userId);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(Collections.singleton(user.getRole())));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
