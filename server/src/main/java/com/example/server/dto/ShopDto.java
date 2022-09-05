package com.example.server.dto;

import com.example.server.entity.Area;

import java.util.Set;

public class ShopDto {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Area> getArea() {
        return area;
    }

    public void setArea(Set<Area> area) {
        this.area = area;
    }

    private Integer id;
    private Set<Area> area;

}
