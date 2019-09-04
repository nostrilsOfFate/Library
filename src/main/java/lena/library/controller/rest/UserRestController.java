package lena.library.controller.rest;


import lena.library.dto.Mapper;
import lena.library.dto.UserDto;
import lena.library.dto.UserRegistrationDto;
import lena.library.model.User;
import lena.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    private final UserService service;


    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> getAll() {
        log.info("Get all users");
        List<User> users = service.findAll();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(user -> dtos.add(Mapper.toUserDto(user)));
        return dtos;
    }

    @GetMapping("/id/{userId}")
    public UserDto findById(@PathVariable Integer userId) {
        log.info("Find user by id: {}", userId);
        return Mapper.toUserDto(service.findById(userId));
    }

    @GetMapping("/email/{email}")
    public UserDto findByEmail(@PathVariable String email) {
        log.info("Find user by email: {}", email);
        return Mapper.toUserDto(service.findByEmail(email));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Integer userId) {
        log.info("Delete user by id: {}", userId);
        service.delete(userId);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        log.info("Create new user: {}", dto.toString());

        UserRegistrationDto registrationDto = new UserRegistrationDto();
        registrationDto.setFirstName(dto.getFirstName());
        registrationDto.setLastName(dto.getLastName());
        registrationDto.setEmail(dto.getEmail());
        registrationDto.setConfirmEmail(dto.getEmail());
        registrationDto.setPassword(dto.getPassword());
        registrationDto.setConfirmPassword(dto.getPassword());
        registrationDto.setTerms(true);

        User user = service.save(registrationDto);
        return user != null ? Mapper.toUserDto(user) : null;
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto dto) {
        log.info("Update user: {}", dto.toString());
        User user = service.update(dto);
        return user != null ? Mapper.toUserDto(user) : null;
    }
}

