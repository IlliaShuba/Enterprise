package com.example.server.service;

import com.example.server.repository.UserRepository;
import com.example.server.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void add(String login, String password, String access_right, String role) throws Exception {
        if (userRepository.findByLogin(login) != null) {
            throw new Exception("Пользователь с таким именем существует");
        }
    }
}
