package com.parksangdo.restapi.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private int userCount = 4;

    static {
        users.add(new User(1, "psd", new Date()));
        users.add(new User(2, "osy", new Date()));
        users.add(new User(3, "kms", new Date()));
        users.add(new User(4, "lth", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return users;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }


}
