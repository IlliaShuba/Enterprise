package com.example.server.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private Set<Area> area;


    public Shop() {
    }

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
}
