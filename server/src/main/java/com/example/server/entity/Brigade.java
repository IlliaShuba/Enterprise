package com.example.server.entity;

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

    /*@OneToOne
    @JoinColumn(name = "worker_id")
    private Worker brigadier;*/

}
