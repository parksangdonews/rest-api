package com.parksangdo.restapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/jpa")
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    // http://localhost:8080/jpa/users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public Resource<User> retieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        // hateos
        Resource<User> userResource = new Resource<>(user.get());
        ControllerLinkBuilder lintTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        userResource.add(lintTo.withRel("all-users"));

        //return user.orElseThrow(new UserNotFoundException(String.format("ID[%s] is not found.", id)));
        return userResource;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }




}
