package com.example.server.entity;

import com.example.server.entity.Ware.*;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @JsonIgnore
    private List<Shop> shops = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private List<Equipment> equipment = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private List<Worker> workers= new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private List<Airplane> airplanes = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private List<Glider> gliders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private List<HangGlider> hangGliders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private List<Helicopter> helicopters = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    @JsonManagedReference
    private List<Missile> missiles = new ArrayList<>();
}
