package com.example.server.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.server.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    Boolean existsByLogin(String login);
}
