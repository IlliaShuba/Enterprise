package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "engineeringStaff")
public class EngineeringStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne
    @MapsId
    private Shop shop;
    @OneToOne
    @MapsId
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
