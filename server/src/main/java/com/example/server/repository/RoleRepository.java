package com.example.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.server.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
