package com.example.server.entity;

import com.example.server.entity.Ware.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
    private Area area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brigade")
    @JsonManagedReference
    private Set<Worker> worker = new HashSet<>();

    @OneToOne(mappedBy = "brigade", cascade = CascadeType.ALL)
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
    private Missile missile;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Set<Worker> getWorker() {
        return worker;
    }

    public void setWorker(Set<Worker> worker) {
        this.worker = worker;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Glider getGlider() {
        return glider;
    }

    public void setGlider(Glider glider) {
        this.glider = glider;
    }

    public HangGlider getHangGlider() {
        return hangGlider;
    }

    public void setHangGlider(HangGlider hangGlider) {
        this.hangGlider = hangGlider;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    public Missile getMissile() {
        return missile;
    }

    public void setMissile(Missile missile) {
        this.missile = missile;
    }
}
