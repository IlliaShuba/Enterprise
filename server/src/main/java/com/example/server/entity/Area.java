package com.example.server.entity;

import com.example.server.entity.Ware.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    @JsonManagedReference
    private List<Brigade> brigades = new ArrayList<>();

    @OneToOne
    private EngineeringStaff head;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    @JsonManagedReference
    private List<EngineeringStaff> master = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    @JsonManagedReference
    private Set<Work> works = new HashSet<>();

}
