package com.example.jwtserving.util;

import com.example.jwtserving.model.Role;
import com.example.jwtserving.model.User;
import com.example.jwtserving.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserUtil {
    private final UserService userService;

    public UserUtil(UserService userService) {
        this.userService = userService;
    }

    public void createUsers() {

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setEnable(true);
        admin.setRoles(Set.of(Role.ADMIN));
        userService.save(admin);

        User user = new User();
        user.setLogin("user");
        user.setPassword("user");
        user.setEnable(true);
        user.setRoles(Set.of(Role.USER));
        userService.save(user);

        User supervisor = new User();
        supervisor.setLogin("supervisor");
        supervisor.setPassword("supervisor");
        supervisor.setEnable(true);
        supervisor.setRoles(Set.of(Role.SUPERVISOR));
        userService.save(supervisor);

        User guest = new User();
        guest.setLogin("guest");
        guest.setPassword("guest");
        guest.setEnable(true);
        guest.setRoles(Set.of(Role.GUEST));
        userService.save(guest);
    }

}
