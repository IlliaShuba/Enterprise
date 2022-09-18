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
@Table(name = "laboratory")
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "laboratories", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Shop> shops = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private Set<Equipment> equipment = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private Set<Airplane> airplanes = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private Set<Glider> gliders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private Set<HangGlider> hangGliders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private Set<Helicopter> helicopters = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private Set<Missile> missiles = new HashSet<>();
}
