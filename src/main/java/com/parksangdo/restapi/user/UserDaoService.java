package com.parksangdo.restapi.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private int userCount = 4;

    static {
        users.add(new User(1, "psd", new Date(), "pass1", "810101-1100110"));
        users.add(new User(2, "osy", new Date(), "pass2", "810101-1100110"));
        users.add(new User(3, "kms", new Date(), "pass3", "810101-1100110"));
        users.add(new User(4, "lth", new Date(), "pass4", "810101-1100110"));
    }

    public List<User> findAll() { // wtf
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    // Del
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
