package com.example.server.repository;

import com.example.server.entity.Role;
import org.springframework.data.repository.CrudRepository;
import com.example.server.entity.User;

import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    Boolean existsByLogin(String login);
    Set<User> queryUserByRoles_Id(Integer id);
}
