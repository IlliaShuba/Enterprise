package com.example.server.entity;

import com.example.server.entity.Ware.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private List<Area> area = new ArrayList<>();

    @OneToOne
    private EngineeringStaff head;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "shop_lab",
            joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "laboratory_id", referencedColumnName = "id"))
    private List<Laboratory> laboratories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private List<Airplane> airplanes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private List<Glider> gliders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private List<HangGlider> hangGliders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private List<Helicopter> helicopters = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private List<Missile> missiles = new ArrayList<>();
}
