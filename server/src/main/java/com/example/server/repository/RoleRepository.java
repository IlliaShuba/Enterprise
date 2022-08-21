package com.example.server.repository;

import com.example.server.models.User;
import org.springframework.data.repository.CrudRepository;

import com.example.server.models.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
