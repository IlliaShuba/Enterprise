package com.example.server.controllers;

import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

   /* public ResponseEntity<?> addUser( @RequestParam String login, @RequestParam String password, @RequestParam String access_right ) {

        // add check for username exists in a DB
        if (userRepository.findByLogin(login) != null) {
            return ResponseEntity.badRequest().body("Exist User");
        }

        // create user object
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);


        Role roles = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return ResponseEntity.ok("Create");

    }*/
}
