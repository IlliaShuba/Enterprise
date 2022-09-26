package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "brigade")
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "area_id")
    @JsonBackReference
    private Area area;
    @OneToOne
    private Worker brigadier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brigade")
    @JsonManagedReference
    private Set<Worker> worker = new HashSet<>();

    /*@OneToOne(mappedBy = "brigade", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Airplane airplane;
    @OneToOne(mappedBy = "brigade", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Glider glider;
    @OneToOne(mappedBy = "brigade", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private HangGlider hangGlider;
    @OneToOne(mappedBy = "brigade", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Helicopter helicopter;
    @OneToOne(mappedBy = "brigade", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Missile missile;*/

}
