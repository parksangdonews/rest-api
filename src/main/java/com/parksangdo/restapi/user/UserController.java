package com.parksangdo.restapi.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id) throws UserNotFoundException {
        User user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] is not found.", String.valueOf(id)));
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        // user -> save
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userDaoService.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] is not found.", String.valueOf(id)));
        }
    }

}
