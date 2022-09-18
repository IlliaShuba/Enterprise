package com.example.server.entity;

import com.example.server.entity.Ware.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Area> area = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false)
    private EngineeringStaff head;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "shop_lab",
            joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "laboratory_id", referencedColumnName = "id"))
    private Set<Laboratory> laboratories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private Set<Airplane> airplanes = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private Set<Glider> gliders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private Set<HangGlider> hangGliders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private Set<Helicopter> helicopters = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonManagedReference
    private Set<Missile> missiles = new HashSet<>();
}
