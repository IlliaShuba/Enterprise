package com.example.server.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.server.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
