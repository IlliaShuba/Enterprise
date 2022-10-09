package com.example.server.repository;

import com.example.server.entity.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends CrudRepository<Shop, Integer>{
    List<Shop> findAll();
}
