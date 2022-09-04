package com.example.server.entity;

import com.example.server.entity.Ware.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brigade")
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brigade")
    private Set<Worker> worker = new HashSet<>();

    @OneToOne
    @MapsId
    private Airplane airplane;
    @OneToOne
    @MapsId
    private Glider glider;
    @OneToOne
    @MapsId
    private HangGlider hangGlider;
    @OneToOne
    @MapsId
    private Helicopter helicopter;
    @OneToOne
    @MapsId
    private Missile missile;



}
