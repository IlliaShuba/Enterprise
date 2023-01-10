package com.example.server.entity.Ware;

import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numberOfEngines;
    private Integer weight;
    private String description;
    private LocalDate startCreate;
    private LocalDate finishCreate;
    private LocalDate startTest;
    private LocalDate finishTest;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airplane")
    @JsonManagedReference
    private List<Work> work = new ArrayList<>();

    @OneToMany(mappedBy = "airplane")
    @JsonManagedReference
    private List<Equipment> equipment = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    @JsonBackReference
    private Laboratory laboratory;
}
