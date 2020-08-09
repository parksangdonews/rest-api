package com.parksangdo.restapi.user;

import org.hibernate.boot.internal.MetadataBuildingContextRootImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.Control;
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


}
